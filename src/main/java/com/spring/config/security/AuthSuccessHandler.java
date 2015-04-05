package com.spring.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

    private AuthenticationSuccessHandler defaultHandler;
    
    public AuthSuccessHandler() {
    }

    public AuthSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if ("true".equals(request.getHeader("X-Login-Ajax-call"))) {
            response.getWriter().print(HttpServletResponse.SC_OK);
            response.getWriter().flush();
        }
        else {
            defaultHandler.onAuthenticationSuccess(request, response, authentication);
        }

    }
}