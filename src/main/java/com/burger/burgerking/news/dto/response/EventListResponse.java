package com.burger.burgerking.news.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EventListResponse {
    private List<EventItemResponse> events;
}
