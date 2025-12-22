package com.burger.burgerking.story.dto.response.wrapper;

import com.burger.burgerking.story.dto.response.BrandResponse;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;

import java.util.List;

public record BrandAndFileMetaDataResponse(
        List<BrandResponse> brandResponseList,
        List<FileMetaDataResponse> fileMetaDataResponseList
) {
}
