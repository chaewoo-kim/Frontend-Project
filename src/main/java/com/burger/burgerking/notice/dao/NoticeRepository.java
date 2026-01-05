package com.burger.burgerking.notice.dao;


import com.burger.burgerking.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice,Long> {

    List<Notice> findAllByOrderByCreatedAtDesc();
}
