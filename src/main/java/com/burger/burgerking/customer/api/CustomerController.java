package com.burger.burgerking.customer.api;


import com.burger.burgerking.customer.application.CustomerService;
import com.burger.burgerking.customer.dto.response.QaCategoryResponse;
import com.burger.burgerking.customer.dto.response.FaqResponse;
import com.burger.burgerking.customer.dto.response.QaSummaryResponse;
import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 고객지원 메인페이지 기능
* - 검색, 카테고리별 링크이동, FAQ 5개
* */
@Tag(name = "고객지원", description = "고객지원 섹션")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer/main")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "문의사항 검색 기능 제공", description = "고객문의 사항 검색 조회")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<QaSummaryResponse>>> search(
            @RequestParam(name="keyword", required = false) String keyword
    ) {
        return ResponseEntity.ok(ApiResponse.success(customerService.search(keyword)));
    }

    @Operation(summary = "문의사항 카테고리 리스트 조회 기능 제공", description = "카테고리 리스트를 통해 문의사항 리스트를 조회할 수 있도록 리스트를 제공")
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<QaCategoryResponse>>> categories(
    ) {
        // 근데 홈페이지 보면 사실상 그냥 QALIST 링크임
        return ResponseEntity.ok(ApiResponse.success(customerService.getCategories()));
    }

    @Operation(summary = "대표 FAQ 조회 기능 제공", description = "문의가 많은 질문 5개를 조회하여 제목을 게시 및 Forward링크 설정")
    @GetMapping("/faq")
    public ResponseEntity<ApiResponse<List<FaqResponse>>> faq(){
        return ResponseEntity.ok(ApiResponse.success(customerService.getFAQTop5()));
    }
}
