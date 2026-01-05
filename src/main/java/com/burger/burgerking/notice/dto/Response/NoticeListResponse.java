package com.burger.burgerking.notice.dto.Response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeListResponse {

    private List<NoticeSummary> notices;


    @Getter
    @Builder
    public static class NoticeSummary {
        private Long noticeId;
        private String title;
        private String createdAt;
    }

}
