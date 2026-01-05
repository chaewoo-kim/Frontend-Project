package com.burger.burgerking.mainpage.api;


import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.mainpage.application.MainpageService;
import com.burger.burgerking.mainpage.dto.request.MainPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "메인페이지", description = "메인페이지 섹션")
@RestController
@RequestMapping("/api/v1/mainpage")
@RequiredArgsConstructor
public class MainpageController {

    private final MainpageService mainpageService;

/*
*   MainPage API
* */
    @Operation(summary = "메인페이지 통합 데이터", description = "이벤트 배너, 광고 영상, Why BurgerKing 자료 등 메인페이지에 필요한 데이터를 한 번에 가져옵니다.")
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<MainPageResponse>> total() {
        MainPageResponse response = mainpageService.getMainPageData();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

/*
*   Redirect Links (Now returning URLs for Frontend to handle)
* */

    @Operation(summary = "버거킹 배달앱 플레이스토어 링크", description = "버거킹 배달앱링크 URL을 반환합니다.")
    @GetMapping("/playstore")
    public ResponseEntity<ApiResponse<String>> playstore(){
        return ResponseEntity.ok(ApiResponse.success("https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid"));
    }

    @Operation(summary = "광고영상-더보기 링크", description = "'더보기'란을 통해 광고영상 페이지로 이동하기 위한 경로를 반환합니다.")
    @GetMapping("/morevideos")
    public ResponseEntity<ApiResponse<String>> morevideos(){
        return ResponseEntity.ok(ApiResponse.success("/story/ad"));
    }

    @Operation(summary = "버거킹 배달앱 앱스토어 링크", description = "버거킹 배달앱링크 URL을 반환합니다.")
    @GetMapping("/appstore")
    public ResponseEntity<ApiResponse<String>> appstore(){
        return ResponseEntity.ok(ApiResponse.success("https://apps.apple.com/kr/app/%EB%B2%84%EA%B1%B0%ED%82%B9-%ED%96%84%EB%B2%84%EA%B1%B0-%ED%82%B9%EC%98%A4%EB%8D%94-%EB%94%9C%EB%A6%AC%EB%B2%84%EB%A6%AC/id1017567032"));
    }

/*
*   Forward Links (Internal Navigation paths as Data)
* */
    @Operation(summary = "메뉴 링크", description = "메뉴페이지 이동을 위한 경로를 반환합니다.")
    @GetMapping("/menu")
    public ResponseEntity<ApiResponse<String>> menu(){
        return ResponseEntity.ok(ApiResponse.success("/menu"));
    }

    @Operation(summary = "매장소개 링크", description = "매장소개 페이지 이동을 위한 경로를 반환합니다.")
    @GetMapping("/store")
    public ResponseEntity<ApiResponse<String>> store(){
        return ResponseEntity.ok(ApiResponse.success("/store"));
    }

    @Operation(summary = "why버거킹 링크", description = "why버거킹 페이지 이동을 위한 경로를 반환합니다.")
    @GetMapping("/why")
    public ResponseEntity<ApiResponse<String>> whyburgerking(){
        return ResponseEntity.ok(ApiResponse.success("/story/why"));
    }

    @Operation(summary = "배달주문번호 링크", description = "전화 주문을 위한 tel 스키마 URL을 반환합니다.")
    @GetMapping("/order")
    public ResponseEntity<ApiResponse<String>> order(){
        return ResponseEntity.ok(ApiResponse.success("tel:1599-0505"));
    }

    @Operation(summary = "브랜드-스토리 링크", description = "브랜드 스토리 페이지 이동을 위한 경로를 반환합니다.")
    @GetMapping("/brand")
    public ResponseEntity<ApiResponse<String>> brand(){
        return ResponseEntity.ok(ApiResponse.success("/story/brand"));
    }

}
