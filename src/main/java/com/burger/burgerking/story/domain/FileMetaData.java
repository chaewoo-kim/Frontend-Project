package com.burger.burgerking.story.domain;

import com.burger.burgerking.global.entity.BaseEntity;
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

    // 4. 파일 크기 (Byte 단위)
    // 관리자가 용량 관리하거나, 다운로드 시 미리 크기를 알려줄 때 유용
    private Long fileSize;

    // 5. 파일 확장자/타입 (MIME Type)
    // 예: "image/jpeg", "application/pdf"
    private String contentType;

    // 6. 연결된 도메인 (선택 사항 - 다형성 고려 시)
    // 예: "REVIEW", "PROFILE", "MENU" - 어떤 용도의 파일인지 구분
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Builder
    public FileMetaData(String originalFilename, String storedFilename, String fileUrl, Long fileSize, String contentType, FileType fileType) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.fileType = fileType;
    }
}
