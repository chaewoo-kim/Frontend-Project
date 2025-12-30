package com.burger.burgerking.notice.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.notice.application.NoticeService;
import com.burger.burgerking.notice.dto.Response.NoticeDetailResponse;
import com.burger.burgerking.notice.dto.Response.NoticeListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "공지사항", description = "버거킹 공지사항 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news/notices")
public class NoticeController {

    private final NoticeService noticeService;


    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<NoticeListResponse>> getNoticeList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{noticeId}")
    public ResponseEntity<ApiResponse<NoticeDetailResponse>> getNoticeDetail(
            @PathVariable Integer noticeId
    ) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}