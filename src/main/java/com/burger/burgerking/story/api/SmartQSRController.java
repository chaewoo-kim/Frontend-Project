package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.dto.response.SmartQSRResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Smart QSR", description = "Smart QSR 섹션")
@RestController
@RequestMapping("/api/v1/story/qsr")
public class SmartQSRController {

    @Operation(summary = "Smart QSR 정보 제공", description = "Smart QSR에 대한 title, subtitle, description, imageUrl 제공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SmartQSRResponse>>> smartQsr() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
