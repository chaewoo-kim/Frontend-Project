package com.burger.burgerking.news.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    public GroupedOpenApi newsApi() {
        return GroupedOpenApi.builder()
                .group("News API")
                .packagesToScan("com.burger.burgerking.news")
                .pathsToMatch("/api/v1/events/**")
                .build();
    }

}
