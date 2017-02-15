package com.coolnimesh43.persistence.config.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.coolnimesh43.persistence.constant.PersistenceConstant;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;
import com.coolnimesh43.persistence.rest.service.ProjectMemberService;

public class XAuthTokenFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private UserDetailsService userDetailsService;
    private ProjectMemberService projectMemberService;
    private TokenProvider tokenProvider;

    private XAuthTokenFilter() {
    }

    private XAuthTokenFilter(Builder builder) {
        this.userDetailsService = builder.userDetailsService;
        this.projectMemberService = builder.projectMemberService;
        this.tokenProvider = builder.tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String authToken = httpServletRequest.getHeader(PersistenceConstant.Auth.X_AUTH_TOKEN);
            String authProject = httpServletRequest.getHeader(PersistenceConstant.Auth.X_AUTH_PROJECT_ID);
            if (StringUtils.hasText(authToken)) {
                String username = this.tokenProvider.getUserNameFromToken(authToken);
                UserDetails details = this.userDetailsService.loadUserByUsername(username);
                if (this.tokenProvider.validateToken(authToken, details)) {
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }

                /**
                 * custom ontarget logic to make sure the request with user and project is valid.
                 */
                String requestPath = ((HttpServletRequest) servletRequest).getRequestURI();
                log.info("Request Path {}", requestPath);

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(details, details.getPassword(), new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);

                for (String endPoint : PersistenceConstant.OPEN_RS_END_POINT) {
                    if (requestPath.contains(endPoint)) {
                        filterChain.doFilter(httpServletRequest, servletResponse);
                        return;
                    }
                }
                if (authProject == null) {
                    ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized Entry");
                    return;
                }
                Long projectId = Long.parseLong(authProject);

                ProjectMemberDTO memberDTO = this.projectMemberService.findOneByProjectIdAndUserLoginAndStatus(projectId, username,
                        PersistenceConstant.Status.ACTIVE);
                if (memberDTO == null) {
                    ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "User is unauthorized to view this project.");
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static class Builder {
        private UserDetailsService userDetailsService;
        private ProjectMemberService projectMemberService;
        private TokenProvider tokenProvider;

        public Builder(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
            super();
            this.userDetailsService = userDetailsService;
            this.tokenProvider = tokenProvider;
        }

        public Builder projectMemberService(ProjectMemberService projectMemberService) {
            this.projectMemberService = projectMemberService;
            return this;
        }

        public XAuthTokenFilter build() {
            return new XAuthTokenFilter(this);
        }
    }

}
