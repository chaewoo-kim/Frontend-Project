package com.burger.burgerking.customer.dto.response;
/**
 * 문의사항 목록 화면에서 사용하는 최소 정보 DTO
 * */
public record QaSummaryResponse(
        Long qaId,
        String title,
        Long categoryId,
        String categoryName
) {
    public static QaSummaryResponse of(Long qaId, String title, Long categoryId, String categoryName) {
        return new QaSummaryResponse(qaId, title, categoryId, categoryName);
    }
}
