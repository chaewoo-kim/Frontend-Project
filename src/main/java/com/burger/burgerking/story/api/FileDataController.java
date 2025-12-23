package com.burger.burgerking.story.api;

import com.burger.burgerking.global.common.ApiResponse;
import com.burger.burgerking.story.application.FileDataService;
import com.burger.burgerking.story.dto.request.ImageRequest;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Story 관련 FileData 전달", description = "Story 섹션에서 사용하는 이미지들을 전달")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class FileDataController {

    private final FileDataService fileDataService;

    @Operation(summary = "섹션에 맞는 이미지 제공", description = "섹션 구성에 필요한 imageUrl 제공")
    @PostMapping
    public ResponseEntity<ApiResponse<List<FileMetaDataResponse>>> brand(@RequestBody ImageRequest request) {
        return ResponseEntity.ok(ApiResponse.success(fileDataService.getInfo(request)));
    }
}
