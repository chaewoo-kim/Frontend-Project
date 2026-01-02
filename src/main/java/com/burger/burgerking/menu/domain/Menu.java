package com.burger.burgerking.menu.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // 메뉴 구성품
    @Column(nullable = false)
    private String menuComposition;

    @Column(length = 100)
    private String description;
    private int kcal;

    private String imageUrl;

    private boolean isNewMenu;        // 신메뉴
    private boolean isLimited;        // 기간한정
    private boolean isPopular;        // 인기
    private boolean isSpicy;          // 매운 메뉴
    private boolean isAllDaySnack;    // All Day Snack
    private boolean isAllDayKing;     // All Day King

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuCategory> menuCategories = new ArrayList<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuKeyword> menuKeywords = new ArrayList<>();

}
