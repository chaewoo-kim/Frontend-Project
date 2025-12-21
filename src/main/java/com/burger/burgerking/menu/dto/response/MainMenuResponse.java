package com.burger.burgerking.menu.dto.response;

import java.util.List;

public record MainMenuResponse(
        List<CategoryMenuResponse> categoryMenuResponseList
) {
}
