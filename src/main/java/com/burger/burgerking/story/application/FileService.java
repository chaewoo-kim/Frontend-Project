package com.burger.burgerking.story.application;

import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dao.FileMetaDataRepository;
import com.burger.burgerking.story.dto.request.FileMeta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final S3Client s3Client;

    private final String bucket = "burgerking";
    private final FileMetaDataRepository fileMetaDataRepository;

    // ✅ 업로드
    public FileMetaData uploadFile(
            MultipartFile file,
            FileMeta fileMeta
    ) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String savedName = UUID.randomUUID() + extension;

        FileMetaData fileMetaData = FileMetaData.builder()
                .originalFilename(originalFilename)
                .storedFilename(savedName)
                .fileUrl("http://dev.macacolabs.site:9000/burgerking/" + savedName)
                .fileType(fileMeta.type())
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(savedName)
                .contentType(file.getContentType())
                .build();

        fileMetaDataRepository.save(fileMetaData);

        s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
        return fileMetaData;
    }

    @Transactional
    public List<FileMetaData> uploadFiles(
            List<MultipartFile> files, FileMeta fileMeta
    ) throws IOException {
        List<FileMetaData> fileMetaDataList = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String savedName = UUID.randomUUID() + extension;

            FileMetaData fileMetaData = FileMetaData.builder()
                    .originalFilename(originalFilename)
                    .storedFilename(savedName)
                    .fileUrl("http://dev.macacolabs.site:9000/burgerking/" + savedName)
                    .fileType(fileMeta.type())
                    .build();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(savedName)
                    .contentType(file.getContentType())
                    .build();

            fileMetaDataRepository.save(fileMetaData);

            s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            fileMetaDataList.add(fileMetaData);
        }

        return fileMetaDataList;
    }

    // ✅ 파일 목록 조회
    public List<String> listFiles() {
        ListObjectsV2Response response = s3Client.listObjectsV2(
                ListObjectsV2Request.builder().bucket(bucket).build()
        );
        return response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    // ✅ 다운로드
    public byte[] downloadFile(String fileName) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request)) {
            return response.readAllBytes();
        }
    }

    // ✅ 삭제
    public void deleteFile(String fileName) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
        log.info("Deleting file {}", fileName);
        s3Client.deleteObject(request);
    }
}
