package com.burger.burgerking.customer.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="tbl_customer")
@Getter
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Long categoryId;

}
