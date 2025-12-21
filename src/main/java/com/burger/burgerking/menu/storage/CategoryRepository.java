package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByOrderByDisplayOrdercAsc();
}
