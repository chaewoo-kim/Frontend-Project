package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.dto.response.BrandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Brand", description = "Brand 섹션")
@RestController
@RequestMapping("/api/v1/story/brand")
public class BrandController {

    @Operation(summary = "Brand 정보 제공", description = "Brand에 대한 title, description, imageUrl 제공")
    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandResponse>>> brand() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
