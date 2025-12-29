package com.burger.burgerking.customer.api;


import com.burger.burgerking.customer.dto.response.QaDetailResponse;
import com.burger.burgerking.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "세부문의사항", description = "세부문의사항 세션")
@RestController
@RequestMapping("/api/v1/customer/detail")
public class DetailController {

    @Operation(summary = "문의사항 개별 조회", description = "Request를 통해 Category와 id를 받아와 개별문의사항을 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<QaDetailResponse>> detail() {
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
