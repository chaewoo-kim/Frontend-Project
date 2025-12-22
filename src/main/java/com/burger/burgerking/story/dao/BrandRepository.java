package com.burger.burgerking.story.dao;

import com.burger.burgerking.story.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
