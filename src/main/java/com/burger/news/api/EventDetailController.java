package com.burger.news.api;

import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "이벤트", description = "버거킹 이벤트/뉴스 조회 API")
@RestController
@RequestMapping("/api/v1/events")
public class EventDetailController {
    @Operation(
            summary = "진행중 이벤트 상세 조회",
            description = "진행중인 이벤트 ID를 통해 이벤트 상세 정보를 조회한다."
    )
    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponse>> getEventDetail(
            @PathVariable Long eventId
    ) {
        log.info("진행중 이벤트 상세 조회 요청 - eventId: {}", eventId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }


    @Operation(
            summary = "종료 이벤트 상세 조회",
            description = "종료된 이벤트 상세 정보를 조회한다."
    )
    @GetMapping("/end/{eventId}")
    public ResponseEntity<ApiResponse<EndEventDetailResponse>> getEndEventDetail(
            @PathVariable Long eventId
    ) {
        log.info("종료된 이벤트 상세 조회 요청 - eventId: {}", eventId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

}

