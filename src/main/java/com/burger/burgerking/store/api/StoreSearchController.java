package com.burger.burgerking.store.api;

import com.burger.burgerking.store.application.StoreSearchService;
import com.burger.burgerking.store.dto.response.StoreListResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreSearchController {

    private final StoreSearchService storeSearchService;

    @Operation(summary = "전체 매장 조회 및 검색 API")
    @GetMapping
    public ResponseEntity<StoreListResponseDTO> getStores(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> services,
            @RequestParam(required = false, defaultValue = "ALL") String membership,
            @RequestParam(required = false, defaultValue = "NAME_ASC") String sort
    ) {
        return ResponseEntity.ok(
                storeSearchService.searchStores(keyword, services, membership, sort)
        );
    }
}
