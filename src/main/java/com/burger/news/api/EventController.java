package com.burger.news.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.news.dto.response.EventListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "이벤트", description = "버거킹 이벤트/뉴스 조회 API")
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Operation(
            summary = "진행중 이벤트 목록 조회",
            description = "진행중인 이벤트 목록을 조회한다."
    )

    @GetMapping
    public ResponseEntity<ApiResponse<EventListResponse>> getEventList(

            @RequestParam(required = false) Integer page,

            @RequestParam(required = false) Integer size
    )

    {
        log.info("진행중인 이벤트 목록 조회 요청 - page: {}, size: {}", page, size);
        return ResponseEntity.ok(ApiResponse.success(null));
    }


    @Operation(
            summary = "종료 이벤트 목록 조회",
            description = "종료된 이벤트 목록을 조회한다."
    )

    @GetMapping("/end")
    public ResponseEntity<ApiResponse<EndEventListResponse>> getEndEventList(
            @RequestParam(required = false) Integer page,

            @RequestParam(required = false) Integer size

    ) {
        log.info("종료된 이벤트 목록 조회 요청 - page: {}, size: {}", page, size);

        return ResponseEntity.ok(ApiResponse.success(null));

    }
}