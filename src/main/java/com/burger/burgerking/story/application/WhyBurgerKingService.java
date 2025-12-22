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
        List<FileMetaDataResponse> responses = new ArrayList<>();
        for (int i = 0; i < request.imageNames().length; i++) {
            responses.add(FileMetaData.from(fileMetaDataRepository.findByOriginalFilename(request.imageNames()[i])));
        }

        return responses;
    }
}
