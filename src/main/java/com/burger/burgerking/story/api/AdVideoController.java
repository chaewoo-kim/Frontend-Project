package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.dto.response.AdVideoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "광고영상", description = "광고영상 섹션")
@RestController
@RequestMapping("/api/v1/story/video")
public class AdVideoController {

    @Operation(summary = "광고영상 전 연도 정보 제공", description = "전 연도의 광고영상 대한 title, description, imageUrl 제공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AdVideoResponse>>> adVideo() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "광고영상 특정 연도 정보 제공", description = "특정 연도의 광고영상 대한 title, description, imageUrl 제공")
    @GetMapping("/{year}")
    public ResponseEntity<ApiResponse<List<AdVideoResponse>>> yearAdVideo(@PathVariable String year) {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
