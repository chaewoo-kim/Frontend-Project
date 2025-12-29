package com.burger.burgerking.customer.dao;

import com.burger.burgerking.customer.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
