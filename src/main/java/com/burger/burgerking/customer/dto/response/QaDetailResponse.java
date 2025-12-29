package com.burger.burgerking.customer.dto.response;

/**
 * 문의사항 상세 화면에서 쓰는 DTO
 * */
public record QaDetailResponse(
        Long qaId,
        String title,
        String content,
        Long categoryId,
        String categoryName
) {
    public static QaDetailResponse of(Long qaId, String title, String content, Long categoryId, String categoryName) {
        return new QaDetailResponse(qaId, title, content, categoryId, categoryName);
    }
}
