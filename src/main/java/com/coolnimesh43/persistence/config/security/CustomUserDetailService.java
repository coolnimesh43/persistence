package com.coolnimesh43.persistence.config.security;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.rest.service.UserService;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserService userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.debug("LoadByUserName for login : {}", login);
        User user = this.userServiceImpl.findByLogin(login);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(login, user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Cannot find user with login: " + login);
        }
    }

}
