package com.burger.burgerking.notice.dto.Response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeListResponse {

    private Long noticeId;
    private String title;

}
