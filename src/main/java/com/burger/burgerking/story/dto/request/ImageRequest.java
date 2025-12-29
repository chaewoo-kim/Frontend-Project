package com.burger.burgerking.story.dto.request;

import com.burger.burgerking.story.enums.FileType;

public record ImageRequest(
        FileType type
) {
}
