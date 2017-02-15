package com.coolnimesh43.persistence.config.async;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.coolnimesh43.persistence.config.security.PersistenceProperties;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private PersistenceProperties persistenceProperties;

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Initializing async task executor.");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(this.persistenceProperties.getAsync().getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(this.persistenceProperties.getAsync().getMaxPoolSize());
        threadPoolTaskExecutor.setQueueCapacity(this.persistenceProperties.getAsync().getQueueCapacity());
        threadPoolTaskExecutor.setThreadNamePrefix("persistence-");
        return new ExceptionHandlingAsyncTaskExecutor(threadPoolTaskExecutor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
