package com.softhaxi.marves.core.authentication;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("ldapAuthProvider")
    private LdapAuthenticationProvider ldapAuthProvider;

    @Autowired
    @Qualifier("daoAuthProvider")
    private DaoAuthenticationProvider daoAuthProvider;

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        User userEntity = userService.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        boolean isLDAPUser = userEntity.isLDAPUser();
        if (isLDAPUser)
            return ldapAuthProvider.authenticate(authentication);
        else
            return daoAuthProvider.authenticate(authentication);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#supports(
     * java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}