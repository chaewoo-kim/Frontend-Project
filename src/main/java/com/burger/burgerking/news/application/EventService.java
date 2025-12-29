package com.burger.burgerking.news.application;

import com.burger.burgerking.news.domain.Event;
import com.burger.burgerking.news.domain.EventStatus;
import com.burger.burgerking.news.dto.response.EventDetailResponse;
import com.burger.burgerking.news.dto.response.EventItemResponse;
import com.burger.burgerking.news.dto.response.EventListResponse;
import com.burger.burgerking.news.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

        private final EventRepository eventRepository;

        public EventListResponse getEvents(EventStatus status) {
            List<Event> events = eventRepository.findAllByStatus(status);

            return EventListResponse.builder()
                    .events(
                            events.stream()
                                .map(this::toItemResponse)
                                .toList())
                    .build();
        }

        public EventDetailResponse getOngoingEventDetail(Long eventId) {
            Event event = eventRepository.findById(eventId).orElseThrow();

            return EventDetailResponse.builder()
                    .eventId(event.getId())
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .imageUrl(event.getThumbnailUrl())
                    .build();
        }

        private EventItemResponse toItemResponse(Event event) {
            return EventItemResponse.builder()
                    .eventId(event.getId())
                    .title(event.getTitle())
                    .thumbnailUrl(event.getThumbnailUrl())
                    .build();
        }
    }
