package com.burger.burgerking.story.application;

import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.request.ImageRequest;
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

@ExtendWith(MockitoExtension.class)
class FileDataServiceTest {
    @InjectMocks
    private FileDataService fileDataService;

    @Mock
    private FileMetaDataRepository fileMetaDataRepository;

    @Test
    @DisplayName("이미지 반환 로직 검증")
    void getImages() {
        // given
        ImageRequest request = new ImageRequest(FileType.BRAND);

        List<FileMetaData> responses = List.of(
                new FileMetaData(
                        "originalname",
                        "storedname",
                        "fileurl",
                        FileType.BRAND
                ),
                new FileMetaData(
                        "originalname",
                        "storedname",
                        "fileurl",
                        FileType.BRAND
                ),
                new FileMetaData(
                        "originalname",
                        "storedname",
                        "fileurl",
                        FileType.BRAND
                )
        );

        given(fileMetaDataRepository.findAllByFileType(request.type())).willReturn(responses);

        // when
        List<FileMetaDataResponse> result = fileDataService.getInfo(request);

        assertNotNull(result);
    }
}