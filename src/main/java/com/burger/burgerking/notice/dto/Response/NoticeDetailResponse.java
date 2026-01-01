package com.burger.burgerking.notice.dto.Response;

import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeDetailResponse {

    private Long noticeId;
    private String title;
    private String content;
    private String imageUrl;
    private String createdAt;

    private List<FileMetaDataResponse> images;

}
