package com.spring.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Service.UserService;
import com.project.Entities.UserMstr;


@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public DatabaseUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserMstr user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }       

        return new DatabaseUserDetails(user);
    }
}
