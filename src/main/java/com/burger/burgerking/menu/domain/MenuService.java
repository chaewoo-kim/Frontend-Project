package com.burger.burgerking.menu.domain;


import com.burger.burgerking.menu.dto.response.CategoryMenuResponse;
import com.burger.burgerking.menu.dto.response.MainMenuResponse;
import com.burger.burgerking.menu.storage.CategoryRepository;
import com.burger.burgerking.menu.storage.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private MenuRepository menuRepository;
    private CategoryRepository categoryRepository;

    public MainMenuResponse getMenu(){
        List<Category> categories = categoryRepository.findAllByOrderByDisplayOrdercAsc();
        List<CategoryMenuResponse> categoryMenuResponseList = new ArrayList<>();
        for(Category category : categories){
            List<Menu> menus = menuRepository.findByCategory(category);
            categoryMenuResponseList.add(new CategoryMenuResponse(category.getId(), category.getName(), menus));
        }
        return new MainMenuResponse(categoryMenuResponseList);
    }

    // 키워드 검색
    public MainMenuResponse getSearchMenu(List<Long> id) {
        List<Menu> searchMenu = menuRepository.findBySearchMenu(id);
        List<Category> categories = categoryRepository.findAllByOrderByDisplayOrdercAsc();
        List<CategoryMenuResponse> categoryMenuResponseList = new ArrayList<>();
        for(Category category : categories){
            List<Menu> menus = new ArrayList<>();
            for(Menu menu : searchMenu){
                if(menu.getCategory().getId().equals(category.getId())){
                    menus.add(menu);
                }
            }
            if (menus.isEmpty()) {
                continue;
            }
            categoryMenuResponseList.add(new CategoryMenuResponse(category.getId(), category.getName(), menus));
        }
        return new MainMenuResponse(categoryMenuResponseList);

    }
}
