package com.sg.surveyShrike.audit;

import com.sg.surveyShrike.domain.payload.UserPayload;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(((UserPayload) authentication.getPrincipal()).getEmail());
    }

    public Optional<String> getCurrentAuditorFirstName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(((UserPayload) authentication.getPrincipal()).getFirstName());
    }

    public Optional<String> getCurrentAuditorLastName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(((UserPayload) authentication.getPrincipal()).getLastName());
    }
}
