package com.burger.burgerking.story.domain;

import com.burger.burgerking.global.entity.BaseEntity;
import com.burger.burgerking.story.dto.response.FileMetaDataResponse;
import com.burger.burgerking.story.enums.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileMetaData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. 원본 파일명 (사용자가 올린 이름)
    // 예: "내_증명사진.jpg" - 다운로드 시 이 이름으로 받아지게 할 때 필요
    @Column(nullable = false)
    private String originalFilename;

    // 2. 저장된 파일명 (UUID 적용)
    // 예: "550e8400-e29b-41d4-a716-446655440000.jpg"
    // MinIO에서 덮어쓰기 방지를 위해 식별 가능한 이름으로 변경해서 저장함
    @Column(nullable = false, unique = true)
    private String storedFilename;

    // 3. 파일 접근 URL (또는 버킷 내 경로 Key)
    // 예: "http://minio-server:9000/bucket/uuid.jpg" 또는 "profiles/uuid.jpg"
    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Builder
    public FileMetaData(String originalFilename, String storedFilename, String fileUrl, FileType fileType) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public static FileMetaDataResponse from(FileMetaData fileMetaData) {
        return new FileMetaDataResponse(
                fileMetaData.getOriginalFilename(),
                fileMetaData.getStoredFilename(),
                fileMetaData.getFileUrl()
        );
    }

    public static List<FileMetaDataResponse> from(List<FileMetaData> fileMetaData) {
        return fileMetaData.stream()
                .map(FileMetaData::from)
                .toList();
    }
}
