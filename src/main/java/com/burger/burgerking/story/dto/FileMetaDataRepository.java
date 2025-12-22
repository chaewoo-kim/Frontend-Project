package com.burger.burgerking.story.dto;

import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import com.burger.burgerking.story.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, Long> {
    FileMetaData findByOriginalFilename(String originalFilename);

    List<FileMetaData> findAllByFileType(FileType type);
}
