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
    private String storTimeWeekend; // 주말 운영시간
    @Column(length = 100)
    private String storTimeHoliday; // 공휴일 운영시간
    @Column(length = 200)
    private String salesHourNote;       // 연중무휴 / Cleaning Day 등 안내 문구

    @Column(length = 50)
    private String todayBusinessTime;   // 매장 주문 가능 시간
    @Column(length = 50)
    private String todayDeliveryTime;   // 딜리버리 주문 가능 시간
    @Column(length = 50)
    private String todayKordTime;       // 킹오더 주문 가능 시간
    @Column(length = 50)
    private String todayKmomTime;           // 픽업 주문 가능 시간

    public Store(
            String storeCode,
            String name,
            String address,
            String phone,
            Double latitude,
            Double longitude,
            boolean membershipAvailable,
            String storTimeDays,        // 평일 (운영시간)
            String storTimeWeekend,     // 주말 (운영시간)
            String storTimeHoliday,     // 공휴일 (운영시간)
            String salesHourNote,       // "월~금 : 10:00~23:00 / 토~일 : 09:00~23:00"
            String todayBusinessTime,   // 매장(주문가능시간)
            String todayDeliveryTime,   // 배달(주문가능시간)
            String todayKordTime,       // 킹오더(주문가능시간)
            String todayKmomTime        // 킹모닝(주문가능시간)
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
        this.storTimeHoliday = storTimeHoliday;
        this.salesHourNote = salesHourNote;
        this.todayBusinessTime = todayBusinessTime;
        this.todayDeliveryTime = todayDeliveryTime;
        this.todayKordTime = todayKordTime;
        this.todayKmomTime = todayKmomTime;
    }
}
