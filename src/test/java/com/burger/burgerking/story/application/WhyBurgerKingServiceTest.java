package com.burger.burgerking.story.application;

import com.burger.burgerking.story.dao.WhyBurgerKingRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.FileMetaDataRepository;
import com.burger.burgerking.story.dto.request.WhyBurgerKingRequest;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import com.burger.burgerking.story.enums.FileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WhyBurgerKingServiceTest {
    @InjectMocks
    private WhyBurgerKingService whyBurgerKingService;

    @Mock
    private FileMetaDataRepository fileMetaDataRepository;

    private WhyBurgerKingRequest request;

    List<FileMetaData> fileMetaData;

    @BeforeEach
    void setUp() {
        request = new WhyBurgerKingRequest(FileType.WHY_BURGERKING);

        fileMetaData = List.of(
                FileMetaData.builder()
                        .originalFilename("originalname")
                        .storedFilename("storedFileName")
                        .fileUrl("url")
                        .build(),
                FileMetaData.builder()
                        .originalFilename("originalname")
                        .storedFilename("storedFileName")
                        .fileUrl("url")
                        .build(),
                FileMetaData.builder()
                        .originalFilename("originalname")
                        .storedFilename("storedFileName")
                        .fileUrl("url")
                        .build()
        );
    }

    @Test
    @DisplayName("특정 이미지 파일들을 잘 가져오는지 확인")
    void getImages() {
        // given
        given(fileMetaDataRepository.findAllByFileType(request.type())).willReturn(fileMetaData);

        // when
        List<FileMetaDataResponse> responses = whyBurgerKingService.getInfo(request);

        // then
        assertNotNull(responses);

        verify(fileMetaDataRepository, times(1)).findAllByFileType(request.type());
    }
}