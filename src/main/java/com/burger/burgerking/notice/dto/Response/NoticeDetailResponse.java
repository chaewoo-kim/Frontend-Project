package com.burger.burgerking.notice.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeDetailResponse {

    private Long noticeId;
    private String title;
    private String content;
}
