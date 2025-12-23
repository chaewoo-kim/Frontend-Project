package com.burger.burgerking.menu.domain;

import jakarta.persistence.*;
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
    private int price;

    private String imageUrl;

    private boolean isNewMenu;        // 신메뉴
    private boolean isLimited;        // 기간한정
    private boolean isPopular;        // 인기
    private boolean isSpicy;          // 매운 메뉴
    private boolean isAllDaySnack;    // All Day Snack
    private boolean isAllDayKing;     // All Day King

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuKeyword> menuKeywords = new ArrayList<>();

}
