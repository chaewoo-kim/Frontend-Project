package com.burger.burgerking.customer.dao;

import com.burger.burgerking.customer.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QaCategoryRepository extends JpaRepository<Category,Long> {
}
