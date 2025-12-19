package com.burger.burgerking.customer.dto;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tbl_category")
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column
    private String categoryName;
}
