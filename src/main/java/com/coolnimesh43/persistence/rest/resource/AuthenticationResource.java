package com.coolnimesh43.persistence.rest.resource;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolnimesh43.persistence.config.security.Token;
import com.coolnimesh43.persistence.config.security.TokenProvider;
import com.coolnimesh43.persistence.constant.PersistenceConstant;
import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.rest.dto.SignInDTO;
import com.coolnimesh43.persistence.rest.dto.SignInResponse;
import com.coolnimesh43.persistence.rest.service.UserService;

@RestController
@RequestMapping(value = "/api/authenticate")
public class AuthenticationResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SignInResponse> authenticate(@RequestBody SignInDTO signInDTO) {
        SignInResponse response = new SignInResponse();
        try {
            User user = this.userService.findByLogin(signInDTO.getLogin());
            if (user != null) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(signInDTO.getLogin(), signInDTO.getPassword());
                Authentication authentication = this.authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new UsernameNotFoundException("User not found with username: {}" + signInDTO.getLogin());
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            log.error("Exception while authenticating users. Exception is: {}", e);
            response.setReturnVal(PersistenceConstant.Response.Value.ERROR);
            response.setReturnMessage("Bad Credentials.");
            return ResponseEntity.badRequest().body(response);
        } catch (Throwable e) {
            log.error("Exception while authenticating user with login: {}. Exception is: {}", e);
            response.setReturnVal(PersistenceConstant.Response.Value.ERROR);
            response.setReturnMessage("Bad Credentials.");
            return ResponseEntity.badRequest().body(response);
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(signInDTO.getLogin());
        Token token = this.tokenProvider.createToken(userDetails);
        response.setReturnVal(PersistenceConstant.Response.Value.SUCCESS);
        response.setToken(token);
        return ResponseEntity.ok(response);
    }
}
