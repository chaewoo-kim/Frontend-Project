package com.burger.burgerking.customer.api;

import com.burger.burgerking.customer.application.CustomerService;
import com.burger.burgerking.customer.dto.response.QaSummaryResponse;
import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "문의사항 목록", description ="문의사항 목록을 카테고리별로 모아서 조회" )
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer/qalist")
public class QalistController {

    private final CustomerService customerService;

    @Operation(summary = "전체 목록", description = "전체 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<QaSummaryResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(customerService.getAllQa()));
    }

    //카테고리별 조회
    @Operation(summary = "카테고리별 목록", description = "카테고리별 목록을 조회")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<QaSummaryResponse>>> getByCategory(
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(ApiResponse.success(customerService.getQaByCategoryId(categoryId)));
    }
}
