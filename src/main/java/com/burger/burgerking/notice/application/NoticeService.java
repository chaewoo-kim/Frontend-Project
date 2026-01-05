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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final FileMetaDataRepository fileMetaDataRepository;

    /**
     * 공지사항 목록 조회
     */
    public NoticeListResponse getNotices(Integer page, Integer size) {

        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;

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
                                                .createdAt(
                                                        notice.getCreatedAt()
                                                                .toLocalDate()
                                                                .toString()
                                                )
                                                .build()
                                )
                                .toList()
                )
                .build();
    }

    /* 공지사항 상세 조회 */
    public List<FileMetaDataResponse> getNoticeDetail() {





        // NOTICE 타입 이미지 전체 조회
        List<FileMetaData> files =
                fileMetaDataRepository.findAllByFileType(FileType.NOTICE);




        List<FileMetaDataResponse> images =
                files.stream()
                        .map(FileMetaData::from)
                        .toList();


        log.info("matched images size={}", images.size());

        return images;
    }
}
