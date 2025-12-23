package com.burger.burgerking.story.application;

import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.dto.request.ImageRequest;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDataService {

    private final FileMetaDataRepository fileMetaDataRepository;

    public List<FileMetaDataResponse> getInfo(ImageRequest request) {
        return FileMetaData.from(fileMetaDataRepository.findAllByFileType(request.type()));
    }
}
