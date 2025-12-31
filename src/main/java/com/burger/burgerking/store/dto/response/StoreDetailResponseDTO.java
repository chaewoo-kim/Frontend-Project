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

        String salesHourNote,
        String todayBusinessTime,
        String deliveryTime,
        String pickupTime
) {
}
