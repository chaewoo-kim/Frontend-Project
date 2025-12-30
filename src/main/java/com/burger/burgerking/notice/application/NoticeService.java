package com.burger.burgerking.notice.application;

import com.burger.burgerking.notice.dao.NoticeRepository;
import com.burger.burgerking.notice.dto.Response.NoticeDetailResponse;
import com.burger.burgerking.notice.dto.Response.NoticeListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeListResponse> getNotices (int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("noticeId").descending());

        return noticeRepository.findAll(pageable)
                .map(notice -> NoticeListResponse.builder()
                        .noticeId(notice.getNoticeId())
                        .title(notice.getTitle())
                        .build());

    }

    public NoticeDetailResponse getNoticeDetail(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("공지사항이 존재하지 않습니다."));

        return NoticeDetailResponse.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }

}
