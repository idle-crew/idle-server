package com.idle.server.domain.auth.repository;

import com.idle.server.domain.auth.exception.UserNotLoginException;
import com.idle.server.domain.user.domain.User;
import com.idle.server.global.security.auth.AuthDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

@Repository
@RequestScope
public class AuthRepository {


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotLoginException();
        }
        return ((AuthDetails) authentication.getPrincipal()).getUser();
    }
}
