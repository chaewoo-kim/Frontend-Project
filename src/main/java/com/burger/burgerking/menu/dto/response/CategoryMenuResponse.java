package com.burger.burgerking.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryMenuResponse {
    private Long categoryId;
    private String categoryName;
    private List<MenuListResponse> menuList;

}
