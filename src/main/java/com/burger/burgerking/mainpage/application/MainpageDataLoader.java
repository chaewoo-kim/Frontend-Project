package com.burger.burgerking.mainpage.application;

import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainpageDataLoader implements CommandLineRunner {

    private final FileMetaDataRepository fileMetaDataRepository;

    @Override
    public void run(String... args) throws Exception {
        if (fileMetaDataRepository.count() == 0) {
            // 1. Event Banners (NEWS)
            fileMetaDataRepository.save(FileMetaData.builder()
                    .originalFilename("Spring Special Event")
                    .storedFilename("event_01.png")
                    .fileUrl("https://mob-prd.burgerking.co.kr/images/event/web/list/2025/09/08/fcc4d8df-bc9c-4de4-a58c-6ef7d3b14061.png")
                    .fileType(FileType.NEWS)
                    .build());

            // 2. Ad Videos (AD_VIDEO)
            fileMetaDataRepository.save(FileMetaData.builder()
                    .originalFilename("New Whopper Commercial")
                    .storedFilename("ad_01.png")
                    .fileUrl("https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/fb8997c7-170d-484c-a48b-fc5e1abe97ba.png")
                    .fileType(FileType.AD_VIDEO)
                    .build());

            // 3. Why BurgerKing Slides (WHY_BURGERKING)
            fileMetaDataRepository.save(FileMetaData.builder()
                    .originalFilename("SERVICE_slide")
                    .storedFilename("why_01.png")
                    .fileUrl("https://mob-prd.burgerking.co.kr/images/story/web/2025/01/11/cd1017b7-1d64-4ee6-b2be-9eb5b4c02889.png")
                    .fileType(FileType.WHY_BURGERKING)
                    .build());

            fileMetaDataRepository.save(FileMetaData.builder()
                    .originalFilename("CLEANILINESS_slide")
                    .storedFilename("why_02.png")
                    .fileUrl("https://mob-prd.burgerking.co.kr/images/story/web/2025/01/11/d9dfa872-9e37-4c76-955c-6c7bd639372b.png")
                    .fileType(FileType.WHY_BURGERKING)
                    .build());

            fileMetaDataRepository.save(FileMetaData.builder()
                    .originalFilename("QUALITY_slide")
                    .storedFilename("why_03.png")
                    .fileUrl("https://mob-prd.burgerking.co.kr/images/story/web/2025/01/11/ffe6021e-201d-49d0-9a7d-61af82f64138.png")
                    .fileType(FileType.WHY_BURGERKING)
                    .build());
        }
    }
}
