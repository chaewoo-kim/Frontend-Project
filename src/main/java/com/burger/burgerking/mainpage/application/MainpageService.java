package com.burger.burgerking.mainpage.application;

import com.burger.burgerking.mainpage.dto.request.AdVideoRequest;
import com.burger.burgerking.mainpage.dto.request.EventRequest;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainpageService {

    private final FileMetaDataRepository fileMetaDataRepository;

    // News-이벤트-이벤트 리스트 전부 받아와야함
    public List<EventRequest> getEvents() {
        List<EventRequest> response = null;
        return response;
    }

    public List<AdVideoRequest> getAdVideos() {
        return fileMetaDataRepository.findAllByFileType(FileType.AD_VIDEO).stream()
                .map(file -> new AdVideoRequest(file.getId(), file.getOriginalFilename(), file.getFileUrl()))
                .collect(Collectors.toList());
    }
}
