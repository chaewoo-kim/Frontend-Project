package com.burger.burgerking.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KeyWordListResponse {
    List<KeyWordResponse> keyWordCategoryList;
    List<KeyWordResponse> keyWordTasteList;
}
