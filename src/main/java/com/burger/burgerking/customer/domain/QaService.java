package com.burger.burgerking.customer.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="tbl_qa")
@Getter
@RequiredArgsConstructor
public class QaService {

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
