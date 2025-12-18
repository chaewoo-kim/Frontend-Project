package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.dto.response.ESGResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ESG 경영", description = "ESG 경영 섹션")
@RestController
@RequestMapping("/api/v1/story/esg")
public class ESGController {

    @Operation(summary = "ESG 경영 정보 제공", description = "ESG 경영에 대한 title, subtitle, description, imageUrl 제공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ESGResponse>>> esg() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}