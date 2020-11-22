package com.softhaxi.marves.core.domain;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.of("SYSTEM");
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal != null) {
            if(principal instanceof LdapUserDetailsImpl) {
                LdapUserDetails ldapUser = (LdapUserDetailsImpl) principal;
                return Optional.of(ldapUser.getUsername());
            }
        }
        return (principal == null || principal.toString().equalsIgnoreCase("anonymousUser")) 
            ? Optional.of("SYSTEM") : Optional.of(principal.toString());
    }

    @Override
    public String toString() {
        return "AuditorAwareImpl []";
    }
    
}
