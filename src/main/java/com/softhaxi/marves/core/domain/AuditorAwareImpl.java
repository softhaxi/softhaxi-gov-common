package com.softhaxi.marves.core.domain;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.of("SYSTEM");
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return (principal == null || principal.toString().equalsIgnoreCase("anonymousUser")) 
            ? Optional.of("SYSTEM") : Optional.of(principal.toString());
    }
    
}
