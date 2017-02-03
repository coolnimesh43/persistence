package com.coolnimesh43.persistence.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import liquibase.integration.spring.SpringLiquibase;

@Component
public class DatabaseConfiguration {

    // @Bean
    // public DataSource dataSource(DataSourceProperties dataSourceProperties) {
    // DriverManagerDataSource dataSource = new DriverManagerDataSource(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(),
    // dataSourceProperties.getPassword());
    // dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
    // return dataSource;
    // }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        return liquibase;
    }

}
