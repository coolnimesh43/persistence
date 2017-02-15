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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.coolnimesh43.persistence" })
@EnableAspectJAutoProxy
@EntityScan("com.coolnimesh43.persistence.entity")
@EnableJpaRepositories("com.coolnimesh43.persistence.repository")
@EnableEurekaClient
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class Application {

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("spring.config.name", "persistent-server");
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
        Environment env = configurableApplicationContext.getEnvironment();
        System.out.println("Starting Discovery Server at \n \n");
        System.out.println("Localhost http://localhost:" + env.getProperty("server.port"));
        System.out.println("External http://" + InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port"));
    }
}
