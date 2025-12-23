package com.burger.burgerking.story.dto.request;

import com.burger.burgerking.story.enums.FileType;
import io.swagger.v3.oas.annotations.media.Schema;

public record FileMeta(
        FileType type
) {
}
