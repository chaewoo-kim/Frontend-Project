package com.burger.burgerking.customer.dao;

import com.burger.burgerking.customer.domain.Qa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QaRepository extends JpaRepository<Qa,Long> {

    /// 카테고리별 문의사항
    List<Qa> findAllByCategoryIdOrderByQaIdDesc(Long categoryId);

    /// 최신 FAQ 5개 보여주기
    List<Qa> findTop5ByOrderByQaIdDesc();

    /// 검색(제목 또는 내용의 키워드로 조회)
    List<Qa> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByQaIdDesc(
            String titleKeyword,
            String contentKeyword
    );
}
