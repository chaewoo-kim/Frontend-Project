package com.burger.burgerking.story.application;

import com.burger.burgerking.story.dao.BrandRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.FileMetaDataRepository;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private FileMetaDataRepository fileMetaDataRepository;

    @Test
    @DisplayName("Brand 정보 반환")
    void getInfo() {
        // given
        Long [] imageIds = {1L, 2L, 3L};

        FileMetaData file1 = FileMetaData.builder()
                .fileUrl("url")
                .storedFilename("저장 이름")
                .originalFilename("원본 이름")
                .build();

        FileMetaData file2 = FileMetaData.builder()
                .fileUrl("url")
                .storedFilename("저장 이름")
                .originalFilename("원본 이름")
                .build();

        FileMetaData file3 = FileMetaData.builder()
                .fileUrl("url")
                .storedFilename("저장 이름")
                .originalFilename("원본 이름")
                .build();

        given(fileMetaDataRepository.findById(1L)).willReturn(Optional.of(file1));
        given(fileMetaDataRepository.findById(2L)).willReturn(Optional.of(file2));
        given(fileMetaDataRepository.findById(3L)).willReturn(Optional.of(file3));

        // when
        List<FileMetaDataResponse> responses = brandService.getInfo();

        // then
        assertNotNull(responses);

        verify(fileMetaDataRepository, times(1)).findById(1L);
        verify(fileMetaDataRepository, times(1)).findById(2L);
        verify(fileMetaDataRepository, times(1)).findById(3L);
    }
}