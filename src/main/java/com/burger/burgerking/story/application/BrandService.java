package com.burger.burgerking.story.application;

import com.burger.burgerking.global.error.CustomException;
import com.burger.burgerking.global.error.ErrorCode;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.FileMetaDataRepository;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final FileMetaDataRepository fileMetaDataRepository;

    public List<FileMetaDataResponse> getInfo() {
        Long [] imageIds = {1L, 2L, 3L};

        List<FileMetaDataResponse> responses = new ArrayList<>();
        for (Long imageId : imageIds) {
            responses.add(FileMetaData.from(fileMetaDataRepository.findById(imageId)
                    .orElseThrow(() -> new CustomException(ErrorCode.IMAGE_NOT_FOUND))));
        }

        return responses;
    }
}
