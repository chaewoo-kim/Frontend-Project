package com.burger.burgerking.news.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventDetailResponse {

    private Long eventId;
    private String title;
    private String description;
    private String imageUrl;

}
