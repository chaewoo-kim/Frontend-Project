package com.burger.burgerking.menu.domain;

import com.burger.burgerking.menu.dto.response.CategoryMenuResponse;
import com.burger.burgerking.menu.dto.response.KeyWordListResponse;
import com.burger.burgerking.menu.dto.response.KeyWordResponse;
import com.burger.burgerking.menu.dto.response.MainMenuResponse;
import com.burger.burgerking.menu.dto.response.MenuDetailResponse;
import com.burger.burgerking.menu.dto.response.MenuListResponse;
import com.burger.burgerking.menu.storage.CategoryRepository;
import com.burger.burgerking.menu.storage.KeywordRepository;
import com.burger.burgerking.menu.storage.MenuKeywordRepository;
import com.burger.burgerking.menu.storage.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final MenuKeywordRepository menuKeywordRepository;

    public MainMenuResponse getMenu() {
        List<Category> categories = categoryRepository.findAllByOrderByDisplayOrderAsc();
        List<CategoryMenuResponse> categoryMenuResponseList = new ArrayList<>();
        for (Category category : categories) {
            List<Menu> menus = menuRepository.findByCategory(category);
            List<MenuListResponse> menuListResponses = new ArrayList<>();
            for (Menu menu : menus) {
                menuListResponses.add(toMenuList(menu));
            }
            categoryMenuResponseList
                    .add(new CategoryMenuResponse(category.getId(), category.getName(), menuListResponses));
        }
        return new MainMenuResponse(categoryMenuResponseList);
    }

    // 키워드 검색
    public MainMenuResponse getSearchMenu(List<Long> id) {
        // 키워드 id와 같은 메뉴(순서 상관없이)를 searchMenu로 받아옴.
        List<Menu> searchMenu = menuRepository.findBySearchMenu(id);
        List<Category> categories = categoryRepository.findAllByOrderByDisplayOrderAsc();
        List<CategoryMenuResponse> categoryMenuResponseList = new ArrayList<>();
        for (Category category : categories) {
            List<MenuListResponse> menuListResponses = new ArrayList<>();
            for (Menu menu : searchMenu) {
                boolean tr = menu.getMenuCategories()
                        .stream()
                        .anyMatch(mc -> mc.getCategory().getId().equals(category.getId()));

                if (tr) {
                    menuListResponses.add(toMenuList(menu));
                }
            }
            // menuListResponses가 비어있으면 해당 카테고리는 출력하지 않음.
            if (menuListResponses.isEmpty()) {
                continue;
            }
            categoryMenuResponseList
                    .add(new CategoryMenuResponse(category.getId(), category.getName(), menuListResponses));
        }
        return new MainMenuResponse(categoryMenuResponseList);
    }

    // 뱃지와 메뉴 정보 메소드
    public MenuListResponse toMenuList(Menu menu) {
        List<String> badges = new ArrayList<>();
        if (menu.isNewMenu())
            badges.add("NEW");
        if (menu.isLimited())
            badges.add("LIMITED");
        if (menu.isPopular())
            badges.add("POPULAR");
        if (menu.isSpicy())
            badges.add("SPICY");
        if (menu.isAllDaySnack())
            badges.add("ALL_DAY_SNACK");
        if (menu.isAllDayKing())
            badges.add("ALL_DAY_KING");

        return new MenuListResponse(
                menu.getId(),
                menu.getName(),
                menu.getMenuComposition(),
                menu.getImageUrl(),
                badges);
    }

    public MenuDetailResponse getMenuDetail(Long id) {
        Menu menu = menuRepository.findById(id).orElse(null);
        // 키워드 추출
        // 방법 1. JPQL 사용
        // List<KeyWordResponse> keywords= menuKeywordRepository.findByMenuId(id)
        // .stream()
        // .map(k-> new KeyWordResponse(k.getId(),k.getKeywordType(),k.getName()))
        // .toList();

        // 방법 2. menu엔티티 메소드 활용
        List<KeyWordResponse> keywords = menu.getMenuKeywords()
                .stream()
                .map(k -> new KeyWordResponse(
                        k.getKeyword().getId(), k.getKeyword().getKeywordType(), k.getKeyword().getName()))
                .toList();
        // 메뉴명과 메뉴 구성품이 같은 경우
        if (menu.getName().equals(menu.getMenuComposition())) {
            return new MenuDetailResponse(
                    menu.getName(),
                    menu.getDescription(),
                    menu.getImageUrl(),
                    menu.getKcal(),
                    keywords,
                    badges(menu));
        } else {
            return new MenuDetailResponse(
                    menu.getName(),
                    menu.getMenuComposition(),
                    menu.getDescription(),
                    menu.getImageUrl(),
                    menu.getKcal(),
                    keywords,
                    badges(menu));
        }

    }

    public List<String> badges(Menu menu) {
        List<String> badges = new ArrayList<>();
        if (menu.isNewMenu())
            badges.add("NEW");
        if (menu.isLimited())
            badges.add("LIMITED");
        if (menu.isPopular())
            badges.add("POPULAR");
        if (menu.isSpicy())
            badges.add("SPICY");
        if (menu.isAllDaySnack())
            badges.add("ALL_DAY_SNACK");
        if (menu.isAllDayKing())
            badges.add("ALL_DAY_KING");
        return badges;
    }

    public KeyWordListResponse getMenuByKeyword() {
        // 모든 키워드를 찾아옴
        List<Keyword> keywords = keywordRepository.findAll();

        List<KeyWordResponse> categoryList = new ArrayList<>();
        List<KeyWordResponse> tasteList = new ArrayList<>();

        for (Keyword keyword : keywords) {
            KeyWordResponse result = new KeyWordResponse(
                    keyword.getId(), keyword.getKeywordType(), keyword.getName());
            if (result.getKeywordType() == KeywordType.CATEGORY) {
                categoryList.add(result);
            } else {
                tasteList.add(result);
            }
        }
        return new KeyWordListResponse(categoryList, tasteList);
    }
}
