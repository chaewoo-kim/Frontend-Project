package com.burger.burgerking.notice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NoticeConfig {

    @Bean
    public GroupedOpenApi noticeApi() {
        return GroupedOpenApi.builder()
                .group("Notice API")
                .packagesToScan("com.burger.burgerking.notice")
                .pathsToMatch("/api/v1/news/**")
                .build();
    }

}
