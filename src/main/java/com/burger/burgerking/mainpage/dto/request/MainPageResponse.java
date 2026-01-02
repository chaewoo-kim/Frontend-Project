package com.burger.burgerking.mainpage.dto.request;

import java.util.List;

public record MainPageResponse(
    List<EventBannerResponse> eventBanners,
    List<AdVideoRequest> adVideos,
    List<WhyBKSlideResponse> whyBkSlides,
    MainPageLinks links
) {
    public record EventBannerResponse(
        Long id,
        String title,
        String imageUrl
    ) {}

    public record MainPageLinks(
        String playStore,
        String appStore,
        String menu,
        String store,
        String whyBk,
        String order,
        String brand,
        String moreVideos
    ) {}
}
