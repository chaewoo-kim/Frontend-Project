package com.burger.burgerking.story.application;

import com.burger.burgerking.story.dao.BrandRepository;
import com.burger.burgerking.story.dto.response.BrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<BrandResponse> getInfo() {


        return null;
    }
}
