package com.learn.SpringApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String tokenHeader = request.getHeader("Authorization");

        if(tokenHeader==null || !tokenHeader.startsWith("Bearer")){
            response.sendError(response.SC_UNAUTHORIZED,"UNAUTHORIZED");
        }
    }
}
