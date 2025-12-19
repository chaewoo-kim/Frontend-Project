package com.burger.burgerking.customer.api;


import com.burger.burgerking.customer.dto.request.QaRequest;
import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "고객지원", description = "고객지원 섹션")
@RestController
@RequestMapping("/api/v1/customer/main")
public class CustomerController {

    @Operation(summary = "문의사항 검색 기능 제공", description = "고객문의 사항 검색 조회")
    @GetMapping("/{content}")
    public ResponseEntity<ApiResponse<List<QaRequest>>> search(
            @PathVariable String content
    ) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "문의사항 카테고리 리스트 조회 기능 제공", description = "카테고리 리스트를 통해 문의사항 리스트를 조회할 수 있도록 리스트를 제공")
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<List<QaRequest>>> searchByCategoryId(
            @PathVariable String categoryId
    ) {
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "대표 문의사항 조회 기능 제공", description = "문의가 많은 질문 5개를 조회하여 제목을 게시 및 Forward링크 설정")
    @GetMapping
    public ResponseEntity<ApiResponse<List<QaRequest>>> search(){

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
