package com.nhparikh.resume.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    protected OpenAPI getOpenApi(@Autowired Info info) {
        return new OpenAPI()
                .info(info);
    }

    @Bean
    @ConfigurationProperties(prefix = "com.nhparikh.resume.swagger")
    protected Info getInfo() {
        return new Info();
    }
}
