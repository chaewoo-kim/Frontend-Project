package com.burger.burgerking.menu.dto.response;

import com.burger.burgerking.menu.domain.Keyword;
import com.burger.burgerking.menu.domain.MenuKeyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class MenuDetailResponse {
    private String name;
    private String menuComposition;
    private String description;
    private String imageUrl;
    private int kcal;
    List<KeyWordResponse> keywords;

    public MenuDetailResponse(String name, String description, String imageUrl, int kcal, List<KeyWordResponse> keywords) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.kcal = kcal;
        this.keywords = keywords;
    }

    public MenuDetailResponse(String name, String menuComposition, String description, String imageUrl, int kcal, List<KeyWordResponse> keywords) {
        this.name = name;
        this.menuComposition = menuComposition;
        this.description = description;
        this.imageUrl = imageUrl;
        this.kcal = kcal;
        this.keywords = keywords;
    }
}
