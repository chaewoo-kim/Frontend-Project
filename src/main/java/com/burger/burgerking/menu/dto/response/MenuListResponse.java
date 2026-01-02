package com.burger.burgerking.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuListResponse {
    private Long id;
    private String name;
    private String menuComposition;
    private String imageUrl;
    private List<String> badges;
}
