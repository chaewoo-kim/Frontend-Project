package com.burger.burgerking.news.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Event {

    private Long id;
    private String thumbnailUrl;
    private String title;
    private String description;
    private String startDate;
    private boolean isNew; //New 뱃지
    private int remainDays; // 7일 남았어요, 5일 남았어요


}