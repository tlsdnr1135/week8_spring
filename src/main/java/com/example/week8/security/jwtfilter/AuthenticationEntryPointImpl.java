package com.example.week8.security.jwtfilter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    //인증이 되지 않은 요청이란?
    /*
        인증절차가 되지 않은 요청.
        사실상 필터에서 이미 막고 있어서 필요가 없음. -> 토큰이 존재하지 않는걸로 나옴.
    */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("AuthenticationEntryPointImpl 안");
        response.setStatus(403);
        response.getWriter().println("인증절차가 되지 않은 요청.");
    }
}
