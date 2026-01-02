package com.burger.burgerking.customer.dto.response;

/**
 * 카테고리 목록 반환
 * */
public record QaCategoryResponse(
    Long categoryId,
    String categoryName
    ) {
    public static QaCategoryResponse of(Long id, String name) {
        return new QaCategoryResponse(id, name);
    }
}
