package com.burger.burgerking.news.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.news.application.EventService;
import com.burger.burgerking.news.domain.EventStatus;
import com.burger.burgerking.news.dto.response.EventDetailResponse;
import com.burger.burgerking.news.dto.response.EventListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@Tag(name = "이벤트", description = "버거킹 이벤트/뉴스 조회 API")
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    // 목록 조회
    @Operation(
            summary = "진행중 이벤트 목록 조회",
            description = "진행중인 이벤트 목록을 조회한다."
    )

    @GetMapping
    public ResponseEntity<ApiResponse<EventListResponse>> getEventList(

            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        eventService.getEvents(EventStatus.ONGOING)
                )
        );
    }


    @Operation(
            summary = "종료 이벤트 목록 조회",
            description = "종료된 이벤트 목록을 조회한다."
    )

    @GetMapping("/end")
    public ResponseEntity<ApiResponse<EventListResponse>> getEndEventList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size

    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        eventService.getEvents(EventStatus.ENDED)
                )
        );

    }

    //상세목록 조회

    @Operation(
            summary = "진행중 이벤트 상세 조회",
            description = "진행중인 이벤트 ID로 상세 정보를 조회한다."
    )
    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponse>> getOngoingEventDetail(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        eventService.getEventDetail(eventId)
                )
        );
    }

    @Operation(
            summary = "종료 이벤트 상세 조회",
            description = "종료된 이벤트 ID로 상세 정보를 조회한다."
    )

    @GetMapping("/end/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponse>> getEndEventDetail(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        eventService.getEventDetail(eventId)

                )
        );
    }
}