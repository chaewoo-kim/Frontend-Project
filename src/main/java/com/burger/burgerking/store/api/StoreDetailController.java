package com.burger.burgerking.store.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.store.dto.response.StoreDetailResponseDTO;
import com.burger.burgerking.store.application.StoreDetailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreDetailController {

    private final StoreDetailService storeDetailService;

    @Operation(summary = "매장 세부정보 조회 API")
    @GetMapping("/{storeCode}")
    public ResponseEntity<ApiResponse<StoreDetailResponseDTO>> getStoreDetail(
            @PathVariable String storeCode
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        storeDetailService.getStoreDetail(storeCode)
                )
        );
    }
}
