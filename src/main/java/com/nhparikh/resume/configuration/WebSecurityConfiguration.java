package com.nhparikh.resume.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    // H2 console
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/h2-console/**").permitAll();
                    // Swagger endpoints
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/api-docs/**").permitAll();
                    // Actuator endpoints
                    authorizationManagerRequestMatcherRegistry.requestMatchers(EndpointRequest.to("health")).permitAll();
                    // API endpoints
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/person").permitAll();
                    // Others
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
