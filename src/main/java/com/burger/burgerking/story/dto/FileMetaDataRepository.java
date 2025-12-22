package com.burger.burgerking.story.dto;

import com.burger.burgerking.story.domain.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, Long> {
}
