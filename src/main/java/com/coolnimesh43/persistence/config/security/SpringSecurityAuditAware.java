package com.coolnimesh43.persistence.config.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Spring jpa audit aware with spring security
 * 
 * @author coolnimesh43
 *
 */
@Component
public class SpringSecurityAuditAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtil.getCurrentUserLogin();
        return (userName != null ? userName : "Admin");
    }

}
