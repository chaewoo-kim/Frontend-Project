package com.burger.burgerking.store.dto.response;

import java.util.List;

public record StoreDetailResponseDTO(
        String storeCode,
        String name,
        String address,
        String phone,
        boolean membershipAvailable,

        List<String> serviceNames,
        List<String> imageUrls,

        /* 운영 시간 */
        String storTimeDays,        // 평일 운영시간
        String storTimeWeekend,     // 주말·공휴일 운영시간
        String storTimeHoliday,
        String salesHourNote,       // 연중무휴 / Cleaning Day 안내

        /* 주문 가능 시간 */
        String todayBusinessTime,   // 매장
        String todayDeliveryTime,   // 딜리버리
        String todayKordTime,       // 킹오더
        String todayKmomTime           // 픽업
) {
}
