package com.burger.burgerking.menu.api;


import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.menu.domain.MenuService;
import com.burger.burgerking.menu.dto.response.MainMenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "메뉴", description = "메뉴페이지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu/main")
public class MenuController {
    private MenuService menuService;
    @GetMapping
    @Operation(summary = "메뉴 전체 조회", description = "메인 메뉴페이지 클릭시 전체메뉴")
    public ResponseEntity<ApiResponse<MainMenuResponse>> getMenu() {
        return ResponseEntity.ok(ApiResponse.success(menuService.getMenu()));
    }

    @GetMapping
    @Operation(summary = "메뉴 검색",description = "키워드 ID로 선택하여 메뉴 검색")
    public ResponseEntity<ApiResponse<MainMenuResponse>> getMenuById(@RequestParam List<Long> id) {
        return ResponseEntity.ok(ApiResponse.success(menuService.getSearchMenu(id)));
    }
}
