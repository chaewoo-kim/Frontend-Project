package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Category;
import com.burger.burgerking.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByCategory(Category category);
}
