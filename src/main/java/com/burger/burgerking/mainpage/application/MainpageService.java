package com.burger.burgerking.mainpage.application;

import com.burger.burgerking.mainpage.dto.request.EventRequest;
import org.springframework.stereotype.Service;

@Service
public class MainpageService {

    // News-이벤트-이벤트 리스트 전부 받아와야함
    public EventRequest getEvents() {
         return new EventRequest();
    }
}
