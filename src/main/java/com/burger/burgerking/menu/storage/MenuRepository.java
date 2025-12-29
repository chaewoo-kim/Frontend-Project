package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Category;
import com.burger.burgerking.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    // JPQL로 ManyToMany/OneToMany 관계 처리
    @Query("SELECT m FROM Menu m JOIN m.menuCategories mc WHERE mc.category = :category")
    List<Menu> findByCategory(@Param("category") Category category);

    @Query("""
    select distinct m
      from Menu m
      join m.menuKeywords mk
     where mk.keyword.id in :id
""")
    List<Menu> findBySearchMenu(@Param("id") List<Long> id);
}
