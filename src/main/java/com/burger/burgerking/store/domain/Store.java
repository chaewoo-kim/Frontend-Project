package com.burger.burgerking.store.domain;

import com.burger.burgerking.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor
public class Store extends BaseEntity {
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

    private String salesHourNote;
    private String todayBusinessTime;
    private String todayDeliveryTime;
    private String pickupTime;

    public Store(
            String storeCode,
            String name,
            String address,
            String phone,
            Double latitude,
            Double longitude,
            boolean membershipAvailable,
            String salesHourNote,
            String todayBusinessTime,
            String todayDeliveryTime,
            String pickupTime
    ) {
        this.storeCode = storeCode;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.membershipAvailable = membershipAvailable;
        this.salesHourNote = salesHourNote;
        this.todayBusinessTime = todayBusinessTime;
        this.todayDeliveryTime = todayDeliveryTime;
        this.pickupTime = pickupTime;
    }
}
