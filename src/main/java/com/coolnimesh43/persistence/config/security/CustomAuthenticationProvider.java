package com.coolnimesh43.persistence.config.security;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.rest.service.UserService;

@Component
// @Qualifier("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserService userService;

    @Inject
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        String login = usernamePasswordAuthenticationToken.getName();
        User user = this.userService.findByLogin(login);
        UserDetails userDetails = this.userDetailService.loadUserByUsername(login);
        String salt = user.getSalt();
        String tokenPassword = (String) usernamePasswordAuthenticationToken.getCredentials();
        String hashedPassword = SecurityUtil.encodePassword(tokenPassword, salt);

        if (hashedPassword != userDetails.getPassword()) {
            throw new BadCredentialsException("Invalid passwword");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return UsernamePasswordAuthenticationToken.class.equals(arg0);
    }

}
