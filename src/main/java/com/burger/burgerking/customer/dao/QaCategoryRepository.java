package com.burger.burgerking.customer.dao;

import com.burger.burgerking.customer.domain.QaCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QaCategoryRepository extends JpaRepository<QaCategory,Long> {
}
