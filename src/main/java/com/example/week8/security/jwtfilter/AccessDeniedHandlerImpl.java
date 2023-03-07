package com.example.week8.security.jwtfilter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //인가 되지 않은 요청이란?
    /*
        허락하지 않은 권한으로 요청했을 경우..
    */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("AccessDeniedHandlerImpl 안");
        response.setStatus(403);
        response.getWriter().println("권한 제한.");
    }
}
