package com.burger.burgerking.store.application;

import com.burger.burgerking.store.domain.*;
import com.burger.burgerking.store.dto.response.StoreDetailResponseDTO;
import com.burger.burgerking.store.dao.StoreImageRepository;
import com.burger.burgerking.store.dao.StoreRepository;
import com.burger.burgerking.store.dao.StoreServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreDetailService {

    private final StoreRepository storeRepository;
    private final StoreServiceRepository storeServiceRepository;
    private final StoreImageRepository storeImageRepository;

    public StoreDetailResponseDTO getStoreDetail(String storeCode) {

        Store store = storeRepository.findByStoreCode(storeCode)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 매장입니다.")
                );

        Long storeId = store.getId();

        List<String> serviceNames =
                storeServiceRepository.findByStoreId(storeId).stream()
                        .map(StoreService::getServiceType)
                        .map(StoreServiceType::getDisplayName)
                        .toList();

        List<String> imageUrls =
                storeImageRepository
                        .findByStoreIdAndImageTypeOrderBySortOrderAsc(
                                storeId,
                                ImageType.MAIN
                        )
                        .stream()
                        .map(StoreImage::getImageUrl)
                        .toList();

        return new StoreDetailResponseDTO(
                store.getStoreCode(),
                store.getName(),
                store.getAddress(),
                store.getPhone(),
                store.isMembershipAvailable(),

                serviceNames,
                imageUrls,

                // 운영 시간
                store.getStorTimeDays(),        // 평일
                store.getStorTimeWeekend(),     // 주말
                store.getStorTimeHoliday(),     // 공휴일
                store.getSalesHourNote(),       // 안내 문구

                // 주문 가능 시간
                store.getTodayBusinessTime(),   // 매장
                store.getTodayDeliveryTime(),   // 딜리버리
                store.getTodayKordTime(),       // 킹오더
                store.getTodayKmomTime()            // 킹모닝
        );
    }
}
