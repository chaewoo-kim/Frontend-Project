package com.burger.burgerking.customer.application;

import com.burger.burgerking.customer.dao.QaCategoryRepository;
import com.burger.burgerking.customer.dao.QaRepository;
import com.burger.burgerking.customer.domain.QaCategory;
import com.burger.burgerking.customer.domain.Qa;
import com.burger.burgerking.customer.dto.response.QaCategoryResponse;
import com.burger.burgerking.customer.dto.response.FaqResponse;
import com.burger.burgerking.customer.dto.response.QaDetailResponse;
import com.burger.burgerking.customer.dto.response.QaSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * 고객 지원 QA 조회 비지니스 로직
 * */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final QaRepository qaRepository;
    private final QaCategoryRepository qaCategoryRepository;

    // CategoryId 와 CategoryName 을 Map에 저장하여 조회하기
    public Map<Long, String> buildCategoryNameMap() {
        List<QaCategory> categories = qaCategoryRepository.findAll();

        return categories.stream()
                .collect(Collectors.toMap(
                        QaCategory::getCategoryId,
                        QaCategory::getCategoryName
                ));
    }

    // 전체 문의사항 목록 조회
    public List<QaSummaryResponse> getAllQa() {
        Map<Long, String> categoryNameMap = buildCategoryNameMap();

        return qaRepository.findAll().stream()
                // 최신순이 필요하면 여기에서 정렬하거나, Repository 메서드를 하나 더 만들어도 됨
                .sorted((a, b) -> Long.compare(b.getQaId(), a.getQaId()))
                .map(qa -> QaSummaryResponse.of(
                        qa.getQaId(),
                        qa.getTitle(),
                        qa.getCategoryId(),
                        categoryNameMap.getOrDefault(qa.getCategoryId(), "기타")
                ))
                .toList();
    }

    // 카테고리 목록
    public List<QaCategoryResponse> getCategories() {
        return qaCategoryRepository.findAll().stream()
                .sorted((a, b) -> Long.compare(a.getCategoryId(), b.getCategoryId()))
                .map(c -> QaCategoryResponse.of(c.getCategoryId(), c.getCategoryName()))
                .toList();
    }

    // 카테고리별 목록
    public List<QaSummaryResponse> getQaByCategoryId(Long categoryId) {
        Map<Long, String> categoryNameMap = buildCategoryNameMap();

        return qaRepository.findAllByCategoryIdOrderByQaIdDesc(categoryId).stream()
                .map(qa-> QaSummaryResponse.of(
                        qa.getQaId(),
                        qa.getTitle(),
                        qa.getCategoryId(),
                        categoryNameMap.getOrDefault(qa.getCategoryId(), "기타")
                ))
                .toList();
    }

    // 검색(제목, 키워드)
    public List<QaSummaryResponse> search(String keyword) {
        // 공백을 보내는 경우
        String q = (keyword == null) ? "" : keyword.trim();

        Map<Long, String> categoryNameMap = buildCategoryNameMap();

        return qaRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByQaIdDesc(q, q)
                .stream()
                .map(qa -> QaSummaryResponse.of(
                        qa.getQaId(),
                        qa.getTitle(),
                        qa.getCategoryId(),
                        categoryNameMap.getOrDefault(qa.getCategoryId(), "기타")
                ))
                .toList();
    }

    // FAQ 대표 5개 제공
    public List<FaqResponse> getFAQTop5() {
        return qaRepository.findTop5ByOrderByQaIdDesc().stream()
                .map(qa -> FaqResponse.of(qa.getQaId(), qa.getTitle()))
                .toList();
    }

    // QA detail 조회
    public QaDetailResponse getDetail(Long qaId) {
        Qa qa = qaRepository.findById(qaId)
                .orElseThrow(() -> new IllegalArgumentException("QA not found: " + qaId));

        // 카테고리 출력
        String categoryName = qaCategoryRepository.findById(qa.getCategoryId())
                .map(QaCategory::getCategoryName)
                .orElse("기타");

        return QaDetailResponse.of(
                qa.getQaId(),
                qa.getTitle(),
                qa.getContent(),
                qa.getCategoryId(),
                categoryName
        );
    }
}
