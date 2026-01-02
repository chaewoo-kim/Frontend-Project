package com.burger.burgerking.mainpage.dto.request;

public record AdVideoRequest(
    Long id,
    String originalFilename,
    String fileUrl
) {
}