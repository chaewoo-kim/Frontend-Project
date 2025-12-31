package com.burger.burgerking.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_service")
@Getter
@NoArgsConstructor
public class StoreService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @Enumerated(EnumType.STRING)
    private StoreServiceType serviceType;

    public StoreService(Long storeId, StoreServiceType serviceType) {
        this.storeId = storeId;
        this.serviceType = serviceType;
    }
}
