package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}
