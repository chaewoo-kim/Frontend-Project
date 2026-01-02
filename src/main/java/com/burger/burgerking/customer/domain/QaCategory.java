package com.burger.burgerking.customer.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tbl_category")
@Getter
public class QaCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column
    private String categoryName;
}
