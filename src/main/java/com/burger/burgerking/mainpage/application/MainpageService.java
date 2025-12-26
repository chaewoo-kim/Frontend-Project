package com.burger.burgerking.mainpage.application;

import com.burger.burgerking.mainpage.dto.request.AdVideoRequest;
import com.burger.burgerking.mainpage.dto.request.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainpageService {

    // News-이벤트-이벤트 리스트 전부 받아와야함
    public List<EventRequest> getEvents() {
        List<EventRequest> response = null;
        return response;
    }

    public List<AdVideoRequest> getAdVideos() {
        List<AdVideoRequest> response = null;
        return response;
    }
}
