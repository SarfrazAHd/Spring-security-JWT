package com.learn.SpringApp.filter;

import com.learn.SpringApp.Interface.UserDetailServiceImpl;
import com.learn.SpringApp.exceptions.InvalidTokenException;
import com.learn.SpringApp.exceptions.TokenHeaderException;
import com.learn.SpringApp.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String token;
        String username;

        if (!(request.getRequestURI().equals("/app/users/generate/token"))) {
            if (tokenHeader != null && tokenHeader.startsWith("Bearer")) {
                token = tokenHeader.substring(7);
                if (token != null) {

                    username = tokenUtil.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    if (tokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    } else {
                        throw new InvalidTokenException("Invalid Token, please try with new token");
                    }
                } else {
                    throw new NullPointerException("Token Null, Authorization header doesn't contain token value");
                }
            } else {
                throw new TokenHeaderException("Authorization header is not present in header..");
            }
            filterChain.doFilter(request, response);
        }else  filterChain.doFilter(request, response);
    }
}

