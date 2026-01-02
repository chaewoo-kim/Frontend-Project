package com.burger.burgerking.news.dto.response;

import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EventDetailResponse {

    private Long eventId;
    private String title;
    private String description;

    // 이미지 여러 장 (MinIO 메타데이터)
    private List<FileMetaDataResponse> files;
}
