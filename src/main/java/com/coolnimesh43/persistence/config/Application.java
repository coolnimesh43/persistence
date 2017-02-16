package com.coolnimesh43.persistence.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.coolnimesh43.persistence.config.security.PersistenceProperties;
import com.coolnimesh43.persistence.config.security.SecurityUtil;

/**
 * Initialized the persistence service application.
 * 
 * @author coolnimesh43
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.coolnimesh43.persistence" })
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableConfigurationProperties({ LiquibaseProperties.class, PersistenceProperties.class })
@EntityScan("com.coolnimesh43.persistence.entity")
@EnableJpaRepositories("com.coolnimesh43.persistence.repository")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class Application extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true).defaultContentType(MediaType.APPLICATION_JSON);
    }

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("spring.config.name", "persistent-server");
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
        Environment env = configurableApplicationContext.getEnvironment();
        String salt = SecurityUtil.generateSecureSalt();
        String password = "admin";
        System.out.print("Salt is : " + salt);
        System.out.println("Password is: " + SecurityUtil.encodePassword(password, salt));
        System.out.println("Starting Discovery Server at \n \n");
        System.out.println("Localhost http://localhost:" + env.getProperty("server.port"));
        System.out.println("External http://" + InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port"));
    }
}
