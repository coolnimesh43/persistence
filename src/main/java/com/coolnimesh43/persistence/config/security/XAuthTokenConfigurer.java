package com.coolnimesh43.persistence.config.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coolnimesh43.persistence.rest.service.ProjectMemberService;

public class XAuthTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private UserDetailsService userDetailsService;
    private ProjectMemberService projectMemberService;
    private TokenProvider tokenProvider;

    private XAuthTokenConfigurer() {
    }

    private XAuthTokenConfigurer(Builder builder) {
        this.userDetailsService = builder.userDetailsService;
        this.projectMemberService = builder.projectMemberService;
        this.tokenProvider = builder.tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        XAuthTokenFilter customFilter = new XAuthTokenFilter.Builder(this.userDetailsService, this.tokenProvider)
                .projectMemberService(this.projectMemberService).build();
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public static class Builder {
        private UserDetailsService userDetailsService;
        private ProjectMemberService projectMemberService;
        private TokenProvider tokenProvider;

        public Builder(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
            this.userDetailsService = userDetailsService;
            this.tokenProvider = tokenProvider;
        }

        public Builder projectMemberService(ProjectMemberService projectMemberService) {
            this.projectMemberService = projectMemberService;
            return this;
        }

        public XAuthTokenConfigurer build() {
            return new XAuthTokenConfigurer(this);
        }
    }
}
