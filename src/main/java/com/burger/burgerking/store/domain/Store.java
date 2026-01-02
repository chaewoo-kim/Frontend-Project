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

    @Column(length = 100)
    private String storTimeDays;        // 평일 운영시간
    @Column(length = 100)
    private String storTimeWeekend;     // 주말/공휴일 운영시간
    @Column(length = 200)
    private String salesHourNote;       // 연중무휴 / Cleaning Day 등 안내 문구

    @Column(length = 50)
    private String todayBusinessTime;   // 매장 주문 가능 시간
    @Column(length = 50)
    private String todayDeliveryTime;   // 딜리버리 주문 가능 시간
    @Column(length = 50)
    private String todayKordTime;       // 킹오더 주문 가능 시간
    @Column(length = 50)
    private String pickupTime;           // 픽업 주문 가능 시간

    public Store(
            String storeCode,
            String name,
            String address,
            String phone,
            Double latitude,
            Double longitude,
            boolean membershipAvailable,
            String storTimeDays,
            String storTimeWeekend,
            String salesHourNote,
            String todayBusinessTime,
            String todayDeliveryTime,
            String todayKordTime,
            String pickupTime
    ) {
        this.storeCode = storeCode;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.membershipAvailable = membershipAvailable;
        this.storTimeDays = storTimeDays;
        this.storTimeWeekend = storTimeWeekend;
        this.salesHourNote = salesHourNote;
        this.todayBusinessTime = todayBusinessTime;
        this.todayDeliveryTime = todayDeliveryTime;
        this.todayKordTime = todayKordTime;
        this.pickupTime = pickupTime;
    }
}
