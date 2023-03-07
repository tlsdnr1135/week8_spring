package com.example.week8.security.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl(filterProcessesUrl);
    }



    //유저네임필터 -> 매니저 -> 프로바이더
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JsonNode requestBody;

        try {
            requestBody = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readTree(request.getInputStream());
        }catch (Exception e){
            throw new AuthenticationException("JSON 데이터 파싱 실패.") {};
        }

        String username = requestBody.get("name").asText();
        String password = requestBody.get("password").asText();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
        setDetails(request,authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
