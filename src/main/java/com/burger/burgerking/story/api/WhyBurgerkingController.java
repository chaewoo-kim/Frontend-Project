package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.dto.response.BrandResponse;
import com.burger.burgerking.story.dto.response.WhyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Why Burgerking", description = "Why Burgerking 섹션")
@RestController
@RequestMapping("/api/v1/story/why")
public class WhyBurgerkingController {

    @Operation(summary = "Why Burgerking 정보 제공", description = "Why Burgerking에 대한 title, subtitle, description, imageUrl 제공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<WhyResponse>>> why() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
