package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Keyword;
import com.burger.burgerking.menu.domain.MenuKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKeywordRepository extends JpaRepository<MenuKeyword,Long> {
    @Query("""
    select distinct mk.keyword
      from MenuKeyword mk
     where mk.menu.id = :id
""")
    List<Keyword>findByMenuId(@Param("id") Long id);

}
