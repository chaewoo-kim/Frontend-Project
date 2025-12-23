package com.burger.burgerking.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String storeCode;

    private String name;
    private String address;
    private String phone;

    private Double latitude;
    private Double longitude;

    private boolean membershipAvailable;
}
