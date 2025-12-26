package com.burger.burgerking.mainpage.api;


import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.mainpage.application.MainpageService;
import com.burger.burgerking.mainpage.dto.request.AdVideoRequest;
import com.burger.burgerking.mainpage.dto.request.EventRequest;
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

    private MainpageService mainpageService;

/*
*   MainPage API
* */
    // 최상단 컨텐츠 ( 이벤트, 앱 다운 쿠폰, 매장찾기 )

    @Operation(summary = "이벤트 링크", description = "이벤트를 리스트로 받아와 스와이프하는 이벤트 도메인. 링크를 클릭하면 Forward 를 통해 해당 이벤트 링크로 이동합니다.")
    @GetMapping("/event")
    public ResponseEntity<ApiResponse<List<EventRequest>>> event(){
            List<EventRequest> response = mainpageService.getEvents();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 광고영상 ( 대표(최신) 광고 3개 & '더보기'란 )
    @Operation(summary = "광고영상-영상 링크", description = "최근 3개의 광고영상을 리스트로 받아와 영상을 조회합니다.")
    @GetMapping("/advideo")
    public ResponseEntity<ApiResponse<List<AdVideoRequest>>> advideo(){
        List<AdVideoRequest> response = mainpageService.getAdVideos();
        return ResponseEntity.ok(ApiResponse.success(response));
    }



/*
*   Redirect Links
* */

    /*
        TODO 앱 다운로드 링크 위치(최상단 & 최하단)
        앱 다운로드 링크(플레이스토어, 앱스토어) - 이미지(QR)
        최상단, 최하단에 위치. 두 곳에서 모두 참조됨
    */
    @Operation(summary = "버거킹 배달앱 플레이스토어 링크", description = "버거킹 배달앱링크로 redirect 이동합니다.")
    @GetMapping("/playstore")
    public String playstore(){
        return "redirect:https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid";
    }

    @Operation(summary = "광고영상-더보기 링크", description = "'더보기'란을 통해 광고영상 페이지로 Forward이동합니다.")
    @GetMapping("/morevideos")
    public String morevideos(){
        return "story/adarchive";
    }

    @Operation(summary = "버거킹 배달앱 앱스토어 링크", description = "버거킹 배달앱링크로 redirect 이동합니다.")
    @GetMapping("/appstore")
    public String appstore(){
        return "redirect:https://apps.apple.com/kr/app/%EB%B2%84%EA%B1%B0%ED%82%B9-%ED%96%84%EB%B2%84%EA%B1%B0-%ED%82%B9%EC%98%A4%EB%8D%94-%EB%94%9C%EB%A6%AC%EB%B2%84%EB%A6%AC/id1017567032";
    }

/*
*   Forward Links
* */
    // 메뉴, 매장소개,why버거킹(메뉴링크, 매장찾기링크, 스토리-why 버거킹)
    @Operation(summary = "메뉴 링크", description = "메인페이지 메뉴소개 파트:메뉴링크 이동(Forward)")
    @GetMapping("/menu")
    public String menu(){
        /*
            TODO 링크확인 1
            리턴 링크 다시 확인해야함. "./" 이 문법이 맞는가?
        */
        return "menu/main";
    }

    @Operation(summary = "매장소개 링크", description = "메인페이지 매장소개 파트:매장소개링크 이동(Forward)")
    @GetMapping("/store")
    public String store(){
        return "store/main";
    }

    @Operation(summary = "why버거킹 링크", description = "why버커깅 링크로 Forward이동")
    @GetMapping("/why")
    public String whyburgerking(){
        return "story/why";
    }

    // 배달링크(이미지)
    @Operation(summary = "배달주문번호 링크", description = "배달 주문 링크를 통해 Redirect 이동")
    @GetMapping("/order")
    public String order(){
        /*
            TODO 링크확인 2
            리턴 링크 다시 확인해야함. "/call" 을 통해 전화를 걸도록 동작시킬 수 있는가?
        */
        return "redirect:call";
    }

    @Operation(summary = "브랜드-스토리 링크", description = "스토리의 브랜드 링크를 통해 Forward 이동")
    @GetMapping("/brand")
    public String brand(){
        return "story/brand";
    }

}
