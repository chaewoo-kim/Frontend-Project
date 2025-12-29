package com.burger.burgerking.customer.dto.response;

/**
 * 카테고리 목록 반환
 * */
public record CategoryResponse (
    Long categoryId,
    String categoryName
    ) {
    public static CategoryResponse of(Long id, String name) {
        return new CategoryResponse(id, name);
    }
}
