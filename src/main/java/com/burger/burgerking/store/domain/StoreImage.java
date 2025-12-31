package com.burger.burgerking.store.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_image")
@Getter
@NoArgsConstructor
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private int sortOrder;

    public StoreImage(
            Long storeId,
            String imageUrl,
            ImageType imageType,
            int sortOrder
    ) {
        this.storeId = storeId;
        this.imageUrl = imageUrl;
        this.imageType = imageType;
        this.sortOrder = sortOrder;
    }
}

