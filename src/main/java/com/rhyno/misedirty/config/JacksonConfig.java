package com.rhyno.misedirty.config;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

@Configuration
public class JacksonConfig {
    /**
     * This reconfiguration should be specified with the following additional requirements.
     * <p>
     * <ol>
     * <li>If a value is {@code null}, its key(i.e. entry) should not be existent.
     * <p>For example, A <i>Schedule</i> consists of a <i>range</i>. The <i>range</i> may be specified or not.</p></li>
     * <li>{@code java.util.Date} serialization within {@code @JsonFormat} has another default timezone
     * so we need to replace it with jdk default timezone.</li>
     * </ol>
     */
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.timeZone(TimeZone.getTimeZone("UTC"));
        builder.modules(new Jdk8Module());      //for lambda function
        builder.modules(new JavaTimeModule());  //for java8 date time module ex.Instant
        return builder;
    }
}
