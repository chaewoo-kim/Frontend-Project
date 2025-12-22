package com.burger.burgerking.story.dto.response;

public record FileMetaDataResponse(
        String originalFileName,
        String storedName,
        String fileUrl
) {
}
