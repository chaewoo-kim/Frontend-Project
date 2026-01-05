package com.burger.burgerking.store.config;

import com.burger.burgerking.store.domain.*;
import com.burger.burgerking.store.dao.StoreImageRepository;
import com.burger.burgerking.store.dao.StoreRepository;
import com.burger.burgerking.store.dao.StoreServiceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class StoreDataLoader implements CommandLineRunner {

    private final StoreRepository storeRepository;
    private final StoreServiceRepository storeServiceRepository;
    private final StoreImageRepository storeImageRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        // ✅ 중복 적재 방지
        if (storeRepository.count() > 0) {
            return;
        }

        // ✅ data 폴더 바로 아래 json 파일들 전부 읽기
        Resource[] resources =
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:data/*.json");

        if (resources.length == 0) {
            throw new IllegalStateException("❌ data 폴더에서 JSON 파일을 찾지 못했습니다.");
        }

        for (Resource resource : resources) {

            JsonNode root = objectMapper.readTree(resource.getInputStream());
            JsonNode stores = root.path("body").path("storInfo");

            for (JsonNode node : stores) {

                Store store = new Store(
                        node.path("storCd").asText(),
                        node.path("storNm").asText(),
                        node.path("storAddr").asText(),
                        node.path("storTelNo").asText(),
                        node.path("storCoordY").asDouble(),
                        node.path("storCoordX").asDouble(),
                        "Y".equals(node.path("storMembershipYn").asText()),
                        node.path("storTimeDays").asText(null),           // 평일 운영시간
                        node.path("storTimeWeekend").asText(null),        // 주말
                        node.path("storTimeHoliday").asText(null),        // 공휴일
                        node.path("salesHourNote").asText(null),          // 하단 설명
                        node.path("todayBusinessTime").asText(null),      // 매장
                        node.path("todayDeliveryTime").asText(null),      // 딜리버리
                        node.path("todayKordTime").asText(null),          // 킹오더
                        node.path("todayKmomTime").asText(null)           // 킹모닝
                );

                storeRepository.save(store);
                Long storeId = store.getId();

                // 서비스 저장
                JsonNode serviceList = node.path("storeServiceCodeList");
                if (serviceList.isArray()) {
                    for (JsonNode svc : serviceList) {
                        StoreServiceType type =
                                StoreServiceType.fromCode(
                                        svc.path("storeServiceCode").asText()
                                );
                        storeServiceRepository.save(
                                new StoreService(storeId, type)
                        );
                    }
                }

                // 썸네일 이미지
                String thumbnail = node.path("storImgThumPath").asText();
                if (!thumbnail.isBlank()) {
                    storeImageRepository.save(
                            new StoreImage(storeId, thumbnail, ImageType.THUMBNAIL, 0)
                    );
                }

                // 메인 이미지 (최대 2장)
                JsonNode mainImages = node.path("storImgMainList");
                int order = 1;
                if (mainImages.isArray()) {
                    for (JsonNode img : mainImages) {
                        if (order > 2) break;
                        String url = img.path("storImgMainPath").asText();
                        if (!url.isBlank()) {
                            storeImageRepository.save(
                                    new StoreImage(storeId, url, ImageType.MAIN, order++)
                            );
                        }
                    }
                }
            }
        }

        System.out.println("✅ 매장 초기 데이터 적재 완료");
    }
}
