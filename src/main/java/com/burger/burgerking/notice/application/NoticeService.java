package com.burger.burgerking.notice.application;

import com.burger.burgerking.notice.dao.NoticeRepository;
import com.burger.burgerking.notice.domain.Notice;
import com.burger.burgerking.notice.dto.Response.NoticeDetailResponse;
import com.burger.burgerking.notice.dto.Response.NoticeListResponse;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import com.burger.burgerking.story.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final FileMetaDataRepository fileMetaDataRepository;

    public NoticeListResponse getNotices(Integer page, Integer size) {

        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        Sort.by("noticeId").descending();

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("noticeId").descending()
        );

        Page<Notice> noticePage = noticeRepository.findAll(pageable);

        return NoticeListResponse.builder()
                .notices(
                        noticePage.getContent().stream()
                                .map(notice ->
                                        NoticeListResponse.NoticeSummary.builder()
                                                .noticeId(notice.getNoticeId())
                                                .title(notice.getTitle())
                                                .build()
                                )
                                .toList()
                )
                .build();
    }

    public NoticeDetailResponse getNoticeDetail(Long noticeId) {

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("공지사항이 존재하지 않습니다.")
                );

        List<FileMetaData> files =
                fileMetaDataRepository.findAllByFileType(FileType.NOTICE);

        List<FileMetaDataResponse> images =
                FileMetaData.from(files);



        return NoticeDetailResponse.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imageUrl(notice.getImageUrl())
                .createdAt(notice.getCreatedAt().toString())
                .images(images)
                .build();
    }
}
