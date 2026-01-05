package com.burger.burgerking.mainpage.application;

import com.burger.burgerking.mainpage.dto.request.AdVideoRequest;
import com.burger.burgerking.mainpage.dto.request.MainPageResponse;
import com.burger.burgerking.mainpage.dto.request.WhyBKSlideResponse;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainpageService {

    private final FileMetaDataRepository fileMetaDataRepository;

    public MainPageResponse getMainPageData() {
        List<FileMetaData> allFiles = fileMetaDataRepository.findAll();

        List<MainPageResponse.EventBannerResponse> eventBanners = allFiles.stream()
                .filter(f -> f.getFileType() == FileType.NEWS)
                .map(f -> new MainPageResponse.EventBannerResponse(f.getId(), f.getOriginalFilename(), f.getFileUrl()))
                .toList();

        List<AdVideoRequest> adVideos = allFiles.stream()
                .filter(f -> f.getFileType() == FileType.AD_VIDEO)
                .map(f -> new AdVideoRequest(f.getId(), f.getOriginalFilename(), f.getFileUrl()))
                .toList();

        List<WhyBKSlideResponse> whyBkSlides = allFiles.stream()
                .filter(f -> f.getFileType() == FileType.WHY_BURGERKING)
                .map(f -> {
                    String title = f.getOriginalFilename();
                    String description = "";
                    
                    // Basic mapping logic based on filename keywords as examples
                    if (title.toUpperCase().contains("SERVICE")) {
                        title = "SERVICE";
                        description = "신속, 정확, 친절한 서비스";
                    } else if (title.toUpperCase().contains("CLEANILINESS")) {
                        title = "CLEANILINESS";
                        description = "철저한 위생 관리";
                    } else if (title.toUpperCase().contains("TRUST")) {
                        title = "맛에 대한 신뢰";
                        description = "Trust in Taste";
                    } else if (title.toUpperCase().contains("WHOPPER")) {
                        title = "버거킹의 자랑, 와퍼!";
                        description = "Real WHOPPER";
                    } else if (title.toUpperCase().contains("FLAMED")) {
                        title = "불맛에 진심인 브랜드";
                        description = "FLAME GRILLED";
                    } else if (title.toUpperCase().contains("QUALITY")) {
                        title = "QUALITY";
                        description = "엄격한 품질 관리 시스템";
                    } else if (title.contains("원재료")) {
                        title = "신선한 원재료 사용";
                        description = "패티와 야채, 소스";
                    }

                    return new WhyBKSlideResponse(f.getId(), title, description, f.getFileUrl());
                })
                .toList();

        MainPageResponse.MainPageLinks links = new MainPageResponse.MainPageLinks(
                "https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid",
                "https://apps.apple.com/kr/app/%EB%B2%84%EA%B1%B0%ED%82%B9-%ED%96%84%EB%B2%84%EA%B1%B0-%ED%82%B9%EC%98%A4%EB%8D%94-%EB%94%9C%EB%A6%AC%EB%B2%84%EB%A6%AC/id1017567032",
                "/menu",
                "/store",
                "/story/why",
                "tel:1599-0505",
                "/story/brand",
                "/story/ad"
        );

        return new MainPageResponse(eventBanners, adVideos, whyBkSlides, links);
    }
}
