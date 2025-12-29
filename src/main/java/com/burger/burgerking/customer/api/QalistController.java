package com.burger.burgerking.customer.api;

import com.burger.burgerking.customer.dto.response.QaSummaryResponse;
import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "문의사항", description ="문의사항 세션" )
@RestController
@RequestMapping("/api/v1/customer/qalist")
public class QalistController {

    @Operation(summary = "문의사항 조회 기능 제공", description = "모든 문의사항을 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<QaSummaryResponse>>> getQalist() {
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
