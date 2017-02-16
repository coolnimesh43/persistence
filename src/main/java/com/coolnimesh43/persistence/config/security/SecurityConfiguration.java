package com.coolnimesh43.persistence.config.security;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.coolnimesh43.persistence.rest.service.ProjectMemberService;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private HttpUnAuthorizedAccessEntryPoint httpUnAuthorizedEntryPoint;

    @Resource(name = "customUserDetailService")
    private UserDetailsService userDetailsService;

    @Resource(name = "customAuthenticationProvider")
    private CustomAuthenticationProvider authenticationProvider;

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private ProjectMemberService projectMemberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider).userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling().authenticationEntryPoint(this.httpUnAuthorizedEntryPoint).and().csrf().disable().headers()
                .frameOptions().disable().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/signup", "/api/authenticate").permitAll().anyRequest().authenticated().and()
                .apply(configurer());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    public XAuthTokenConfigurer configurer() {
        return new XAuthTokenConfigurer.Builder(userDetailsService, tokenProvider).projectMemberService(this.projectMemberService).build();
    }
}
