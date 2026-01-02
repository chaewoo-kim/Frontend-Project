package com.burger.burgerking.news.application;

import com.burger.burgerking.news.domain.Event;
import com.burger.burgerking.news.domain.EventStatus;
import com.burger.burgerking.news.dto.response.EventDetailResponse;
import com.burger.burgerking.news.dto.response.EventItemResponse;
import com.burger.burgerking.news.dto.response.EventListResponse;
import com.burger.burgerking.news.repository.EventRepository;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import com.burger.burgerking.story.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final FileMetaDataRepository fileMetaDataRepository;

    /**
     * 이벤트 목록 조회 (진행중 / 종료)
     * 👉 목록에서는 썸네일 1장만 사용
     */
    public EventListResponse getEvents(EventStatus status) {

        List<Event> events = eventRepository.findAllByStatus(status);

        return EventListResponse.builder()
                .events(
                        events.stream()
                                .map(this::toItemResponse)
                                .toList()
                )
                .build();
    }

    /**
     * 이벤트 상세 조회
     * 👉 MinIO에 저장된 이미지 메타데이터 리스트 반환
     */
    public EventDetailResponse getEventDetail(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException("이벤트가 존재하지 않습니다.")
                );

        // ✅ 이벤트에 연결된 이미지 메타데이터 조회
        List<FileMetaDataResponse> files =
                fileMetaDataRepository
                        .findAllByFileType(FileType.NEWS)
                        .stream()
                        .map(FileMetaData::from)
                        .toList();

        return EventDetailResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .files(files)
                .build();
    }

    /**
     * 이벤트 목록 카드용 DTO
     */
    private EventItemResponse toItemResponse(Event event) {
        return EventItemResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .thumbnailUrl(event.getThumbnailUrl())
                .build();
    }
}
