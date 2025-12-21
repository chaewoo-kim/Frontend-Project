package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Category;
import com.burger.burgerking.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByCategory(Category category);
    @Query("""
    select distinct m
      from Menu m
      join m.menuKeywords mk
     where mk.keyword.id in : id
""")
    List<Menu> findBySearchMenu(@Param("id") List<Long> id);
}
