package com.burger.burgerking.story.application;

import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.FileMetaDataRepository;
import com.burger.burgerking.story.dto.request.WhyBurgerKingRequest;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WhyBurgerKingService {

    private final FileMetaDataRepository fileMetaDataRepository;

    public List<FileMetaDataResponse> getInfo(WhyBurgerKingRequest request) {
        return FileMetaData.from(fileMetaDataRepository.findAllByFileType(request.type()));
    }
}
