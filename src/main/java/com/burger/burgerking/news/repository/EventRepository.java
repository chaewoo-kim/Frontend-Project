package com.burger.burgerking.news.repository;

import com.burger.burgerking.news.domain.Event;
import com.burger.burgerking.news.domain.EventStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class EventRepository {

    public List<Event> findAllByStatus(EventStatus status) {
        return List.of();

    }
    // 더미 구현
    public Optional<Event> findById(Long id) {
        return Optional.of(new Event());
    }

}
