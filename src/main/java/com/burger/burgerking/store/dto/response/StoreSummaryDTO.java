package com.burger.burgerking.store.dto.response;

import com.burger.burgerking.store.domain.Store;

public record StoreSummaryDTO(
        String storeCode,
        String name,
        String address,
        String businessTime,
        boolean membershipAvailable,
        String thumbnailImageUrl
) {

    public static StoreSummaryDTO from(Store store, String thumbnailUrl) {
        return new StoreSummaryDTO(
                store.getStoreCode(),
                store.getName(),
                store.getAddress(),
                store.getSalesHourNote(),
                store.isMembershipAvailable(),
                thumbnailUrl
        );
    }
}
