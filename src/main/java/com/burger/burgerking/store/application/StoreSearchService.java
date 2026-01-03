package com.burger.burgerking.store.application;

import com.burger.burgerking.store.domain.*;
import com.burger.burgerking.store.dao.StoreImageRepository;
import com.burger.burgerking.store.dao.StoreRepository;
import com.burger.burgerking.store.dao.StoreServiceRepository;
import com.burger.burgerking.store.dto.response.StoreListResponseDTO;
import com.burger.burgerking.store.dto.response.StoreSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreSearchService {
    private final StoreRepository storeRepository;
    private final StoreServiceRepository storeServiceRepository;
    private final StoreImageRepository storeImageRepository;

    @Transactional(readOnly = true)
    public StoreListResponseDTO searchStores(String keyword, List<String> services, String membership, String sort) {
        List<Store> stores = storeRepository.findAll();

        if(keyword != null && !keyword.isBlank()){ //keyword와 비교 검색
            stores = stores.stream()
                    .filter(store ->
                            store.getName().contains(keyword) || store.getAddress().contains(keyword))
                    .collect(Collectors.toList());
        }

        if(services != null && !services.isEmpty()){ //서비스
            List<StoreServiceType> serviceTypes =
                    services.stream()
                            .map(String::trim)
                            .map(StoreServiceType::from)
                            .toList();
            Set<Long> matchedStoreIdSet =
                    new HashSet<>(
                            storeServiceRepository.findStoreIdsByAllServiceTypes(
                                    serviceTypes,
                                    serviceTypes.size()
                            )
                    );

            stores = stores.stream()
                    .filter(store -> matchedStoreIdSet.contains(store.getId()))
                    .toList();
        }

        if (!"ALL".equalsIgnoreCase(membership)) {
            boolean target = "Y".equalsIgnoreCase(membership);
            stores = stores.stream()
                    .filter(store -> store.isMembershipAvailable() == target)
                    .collect(Collectors.toList());
        }

        if("NAME_ASC".equalsIgnoreCase(sort)){ //대소문자 무시
            stores.sort(Comparator.comparing(Store::getName));
        }

        List<Long> storeIds = stores.stream().map(Store::getId).toList();
        Map<Long, String> thumbnailMap =
                storeImageRepository.findThumbnailTuplesByStoreIds(storeIds)
                        .stream()
                        .collect(Collectors.toMap(
                                row -> (Long) row[0],   // storeId
                                row -> (String) row[1] // imageUrl
                        ));

        Map<Long, List<String>> serviceMap =
                storeServiceRepository.findByStoreIds(storeIds).stream()
                        .collect(Collectors.groupingBy(
                                StoreService::getStoreId,
                                Collectors.mapping(
                                        ss -> ss.getServiceType().getDisplayName(),
                                        Collectors.toList()
                                )
                        ));


        List<StoreSummaryDTO> result = stores.stream()
                .map(store -> StoreSummaryDTO.from(
                        store,
                        thumbnailMap.get(store.getId()),
                        serviceMap.getOrDefault(store.getId(),List.of())
                ))
                .toList();
        return new StoreListResponseDTO(result.size(), result);
    }
}
