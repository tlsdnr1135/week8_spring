package com.example.week8.security.jwtfilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import com.example.week8.enums.AccountRoleEnum;
import com.example.week8.security.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.example.week8.security.filter.LoginSucessHandler.*;


public class JwtFilter extends OncePerRequestFilter {


    public static final String BEARER_PREFIX = "BEARER ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException,AuthenticationException {
        try{

            String token = request.getHeader(HEADER_NAME); //헤더 가져옴

            //토큰이 존재하는지 확인.
            if (token == null || token.length() == 0) {
                throw new JwtException("토큰이 존재하지 않음");
            }
            
            String jwtToken = token.substring(BEARER_PREFIX.length(), token.length()); //JWT - BEARER 자름.
            DecodedJWT decodedJWT = getDecodedJWT(jwtToken); //jwt토큰 복호화

            UserDetailsImpl userDetails = getUserDetails(decodedJWT);//jwt 파싱해서 userDetails형태로 만들기
            setSecurityHolder(userDetails); //유저정보 시큐리티 홀더 안에 넣기

            filterChain.doFilter(request,response);

        }catch (JwtException e){
            setErrorResponse(e,response);
        }catch (JWTVerificationException e){
            setErrorResponse(e,response);
        }
    }

    //파싱
    private static UserDetailsImpl getUserDetails(DecodedJWT decodedJWT) {
        String username = decodedJWT.getClaim(CLAIM_USER_NAME).asString(); //날짜 파싱.
        String role = decodedJWT.getClaim(CLAIM_ROLE).asString(); //유저네임 파싱.

        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .username(username)
                .role(AccountRoleEnum.valueOf(role))
                .build();
        return userDetails;
    }

    //JWT 토큰 복호화
    private static DecodedJWT getDecodedJWT(String jwtToken) {
        DecodedJWT decodedJWT;

        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRECT_KEY); //비밀키.
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            decodedJWT = verifier.verify(jwtToken); //유효한지 아닌지 확인.
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("토큰이 유효하지 않음");
        }
        return decodedJWT;
    }

    private void setSecurityHolder(UserDetailsImpl userDetails) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private void setErrorResponse(Exception e,HttpServletResponse response)throws IOException{
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(e.getMessage());
        response.setStatus(401);
    }

    //필터 제외 시키기.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/api/account/join","/api/account/all"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

}
