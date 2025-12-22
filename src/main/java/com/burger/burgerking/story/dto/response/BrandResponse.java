package com.burger.burgerking.story.dto.response;

import com.burger.burgerking.story.domain.Brand;

public record BrandResponse(
        String title,
        String description,
        Long imageId
) {
    public static BrandResponse from(Brand brand) {
        return new BrandResponse(
                brand.getTitle(),
                brand.getDescription(),
                brand.getImageId()
        );
    }
}
