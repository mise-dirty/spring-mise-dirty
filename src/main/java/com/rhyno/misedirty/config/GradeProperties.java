package com.rhyno.misedirty.config;

import com.rhyno.misedirty.model.Criteria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "application.criteria", ignoreUnknownFields = false)
public class GradeProperties {
    private Criteria pm10;
    private Criteria pm25;
    private Criteria so2;
    private Criteria co;
    private Criteria o3;
    private Criteria no2;
}
