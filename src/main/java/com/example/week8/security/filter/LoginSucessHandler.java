package com.example.week8.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.week8.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.example.week8.security.jwtfilter.JwtFilter.BEARER_PREFIX;


public class LoginSucessHandler implements AuthenticationSuccessHandler {

    public static final String ISSUER_NAME = "11H_11M";
    public static final String CLAIM_USER_ID = "USER_ID";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String CLAIM_ROLE = "USER_ROLE";
    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String SECRECT_KEY = "sulbin_secretkey";

    public static final String HEADER_NAME = "ImSulbinHeader";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = makeJWT(userDetails);

        response.addHeader(HEADER_NAME,BEARER_PREFIX + token);

        String role = authentication.getAuthorities().toString();
        int size = role.length();
        String sdsds = role.substring(1,size-1);

        System.out.println(size);
        System.out.println(sdsds);

        response.addHeader("ROLE_GROUP", sdsds);
        response.getWriter().println("sdsd");
        response.setStatus(200);
    }

    private static String makeJWT(UserDetailsImpl userDetails) {
        try{
            String tokenTemp = JWT.create()
                    .withIssuer(ISSUER_NAME)
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withClaim(CLAIM_ROLE,String.valueOf(userDetails.getRole()))
                    .withClaim(CLAIM_EXPIRED_DATE,new Date(System.currentTimeMillis() + 30000))
                    .sign(Algorithm.HMAC256(SECRECT_KEY))
                    ;
            return tokenTemp;
        }catch (Exception e){
            throw new AuthenticationException("onAuthenticationSuccess 실패.") {};
        }
    }

}
