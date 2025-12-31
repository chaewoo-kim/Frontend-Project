package com.burger.burgerking.news.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventItemResponse {

    // 이벤트 카드
    private Long eventId;
    private String title;
    private String thumbnailUrl;

}
