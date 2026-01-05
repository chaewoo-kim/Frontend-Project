package com.burger.burgerking.store.dto.response;

import com.burger.burgerking.store.domain.Store;

import java.util.List;

public record StoreSummaryDTO(
        String storeCode,
        String name,
        String address,
        String businessTime,
        boolean membershipAvailable,
        String thumbnailImageUrl,
        List<String> serviceNames
        ) {

    public static StoreSummaryDTO from(
            Store store, String thumbnailUrl, List<String> serviceNames) {
        return new StoreSummaryDTO(
                store.getStoreCode(),
                store.getName(),
                store.getAddress(),
                store.getSalesHourNote(),
                store.isMembershipAvailable(),
                thumbnailUrl,
                serviceNames
        );
    }
}
