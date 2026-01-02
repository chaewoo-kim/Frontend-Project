package com.burger.burgerking.menu.storage;

import com.burger.burgerking.menu.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}
