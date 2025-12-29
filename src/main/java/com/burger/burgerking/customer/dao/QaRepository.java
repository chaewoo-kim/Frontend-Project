package com.burger.burgerking.customer.dao;

import com.burger.burgerking.customer.domain.Category;
import com.burger.burgerking.customer.domain.QaService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QaRepository extends JpaRepository<Category,Long> {

    /// 카테고리별 문의사항
    List<QaService> findAllByCategoryIdOrderByQaIdDesc(Long categoryId);

    /// 최신 FAQ 5개 보여주기
    List<QaService> findTop5ByOrderByQaIdDesc();

    /// 검색(제목 또는 내용의 키워드로 조회)
    ///  String titleKeyword
    ///
    List<QaService> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByQaIdDesc(
            String titleKeyword,
            String contentKeyword
    );
}
