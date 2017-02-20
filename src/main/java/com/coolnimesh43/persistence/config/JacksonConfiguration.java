package com.coolnimesh43.persistence.config;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.coolnimesh43.persistence.entity.util.JSR310DateTimeSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        return new Jackson2ObjectMapperBuilder().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true).modulesToInstall(module);
    }
}
