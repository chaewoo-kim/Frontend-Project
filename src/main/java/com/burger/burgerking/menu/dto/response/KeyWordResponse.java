package com.burger.burgerking.menu.dto.response;

import com.burger.burgerking.menu.domain.KeywordType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyWordResponse {
    private Long id;
    KeywordType keywordType;
    String name;
}
