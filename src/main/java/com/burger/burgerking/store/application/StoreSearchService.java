package com.burger.burgerking.store.application;

import com.burger.burgerking.store.domain.*;
import com.burger.burgerking.store.dto.response.StoreDetailResponseDTO;
import com.burger.burgerking.store.dao.StoreImageRepository;
import com.burger.burgerking.store.dao.StoreRepository;
import com.burger.burgerking.store.dao.StoreServiceRepository;
import com.burger.burgerking.store.dto.response.StoreListResponseDTO;
import com.burger.burgerking.store.dto.response.StoreSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreSearchService {
    private final StoreRepository storeRepository;
    private final StoreServiceRepository storeServiceRepository;
    private final StoreImageRepository storeImageRepository;

    public StoreListResponseDTO searchStores(String keyword, List<String> services, String membership, String sort) {
        List<Store> stores = storeRepository.findAll();

        if(keyword != null && !keyword.isBlank()){
            stores = stores.stream()
                    .filter(store ->
                            store.getName().contains(keyword) || store.getAddress().contains(keyword))
                    .collect(Collectors.toList());
        }

        if(services != null && !services.isEmpty()){
            List<StoreServiceType> serviceTypes =
                    services.stream()
                            .map(StoreServiceType::valueOf)
                            .toList();
            stores = stores.stream()
                    .filter(store ->
                            storeServiceRepository
                                    .countByStoreIdAndServiceTypeIn(
                                            store.getId(), serviceTypes
                                    ) == serviceTypes.size()
                    )
                    .collect(Collectors.toList());
        }

        if (!"ALL".equalsIgnoreCase(membership)) {
            boolean target = "Y".equalsIgnoreCase(membership);
            stores = stores.stream()
                    .filter(store -> store.isMembershipAvailable() == target)
                    .collect(Collectors.toList());
        }

        if("NAME_ASC".equalsIgnoreCase(sort)){
            stores.sort((a,b) ->a.getName().compareTo(b.getName()));
        }

        List<StoreSummaryDTO> result = stores.stream()
                .map(store -> StoreSummaryDTO.from(
                        store,
                        storeImageRepository
                                .findThumbnailByStoreId(store.getId())
                                .orElse(null)
                ))
                .collect(Collectors.toList());
        return new StoreListResponseDTO(result.size(), result);
    }
}
