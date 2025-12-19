package com.burger.burgerking.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // story 관련 API
    @Bean
    public GroupedOpenApi storyGroup() {
        return GroupedOpenApi.builder()
                .group("Story API")
                .pathsToMatch("/api/v1/story/**")
                .build();
    }

    // mainpage 관련 API
    @Bean
    public GroupedOpenApi mainpageGroup() {
        return GroupedOpenApi.builder()
                .group("Mainpage API")
                .pathsToMatch("/api/v1/mainpage/**")
                .build();
    }

    // mainpage 관련 API
    @Bean
    public GroupedOpenApi customerGroup() {
        return GroupedOpenApi.builder()
                .group("Customer API")
                .pathsToMatch("/api/v1/customer/**")
                .build();
    }
    @Bean
    public GroupedOpenApi allGroup() {
        return GroupedOpenApi.builder()
                .group("전체 API")
                .pathsToMatch("/**")
                .build();
    }
}