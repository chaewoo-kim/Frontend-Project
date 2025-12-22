package com.burger.burgerking.menu.dto.response;

import com.burger.burgerking.menu.domain.Keyword;
import com.burger.burgerking.menu.domain.MenuKeyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuDetailResponse {
    private String name;
    private String description;
    private String imageUrl;
    private int kcal;
    List<KeyWordResponse> keywords;
}
