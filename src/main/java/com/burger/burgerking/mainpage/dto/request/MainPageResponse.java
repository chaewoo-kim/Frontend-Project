package com.burger.burgerking.mainpage.dto.request;

import java.util.List;

public record MainPageResponse(
    List<EventBannerResponse> eventBanners,
    List<AdVideoRequest> adVideos,
    List<WhyBKSlideResponse> whyBkSlides
) {
    public record EventBannerResponse(
        Long id,
        String title,
        String imageUrl
    ) {}
}
