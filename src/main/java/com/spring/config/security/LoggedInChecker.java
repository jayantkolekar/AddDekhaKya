package com.spring.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.project.Entities.UserMstr;

@Component
public class LoggedInChecker {
    public UserMstr getLoggedInUser() {
    	UserMstr user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // principal can be "anonymousUser" (String)
            if (principal instanceof DatabaseUserDetails) {
                DatabaseUserDetails userDetails = (DatabaseUserDetails) principal;
                user = userDetails.getUser();
            }
        }

        return user;
    }
}
