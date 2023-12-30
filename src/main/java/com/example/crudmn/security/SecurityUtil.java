package com.example.crudmn.security;

import com.example.crudmn.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {

    public static Optional<UserDetailsImpl> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetailsImpl) {
                        return (UserDetailsImpl) authentication.getPrincipal();
                    }
                    return null;
                });
    }
}
