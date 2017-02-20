package com.coolnimesh43.persistence.config.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

/**
 * Spring jpa audit aware with spring security
 * 
 * @author coolnimesh43
 *
 */
@Service("springSecurityAuditorAware")
public class SpringSecurityAuditAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        System.out.println("Get current auditor called.");
        String userName = SecurityUtil.getCurrentUserLogin();
        return (userName != null ? userName : "admin");
    }

}
