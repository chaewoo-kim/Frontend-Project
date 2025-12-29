package com.burger.burgerking.store.dto.response;


import java.util.List;

public record StoreListResponseDTO(
        int totalCount,
        List<StoreSummaryDTO> stores

) {
}
