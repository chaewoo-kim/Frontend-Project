package com.burger.burgerking.customer.dto.response;

/**
 * 메인페이지 FAQ에 보여줄 DTO
 * - 제목만 보여주고 클릭하여 Detail 페이지로 이동
 * */
public record FaqResponse(
        Long qaId,
        String title
) {
    public static FaqResponse of(Long qaId, String title) {
        return new FaqResponse(qaId, title);
    }
}
