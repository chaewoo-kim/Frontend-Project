package com.burger.burgerking.story.api;

import com.burger.burgerking.story.application.FileService;
import com.burger.burgerking.story.domain.FileMetaData;
import com.burger.burgerking.story.dto.request.FileMeta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Tag(name = "파일 관리", description = "파일 업로드, 조회, 삭제, 다운로드 API")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Operation(description = "파일 업로드")
    @PostMapping(value = "/upload",consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileMetaData> upload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("fileMeta") FileMeta fileMeta
    ) throws IOException {
        log.info("fileMeta: {}",  fileMeta);
        FileMetaData fileMetaData = fileService.uploadFile(file, fileMeta);
        return ResponseEntity.ok(fileMetaData);
    }

    @Operation(description = "여러 파일 한 번에 업로드")
    @PostMapping(value = "/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FileMetaData>>  uploadImages(
            @RequestPart("file") List<MultipartFile> files,
            @RequestPart("fileMeta") FileMeta fileMeta
    ) throws IOException {
        List<FileMetaData> fileMetaDataList = fileService.uploadFiles(files, fileMeta);
        return ResponseEntity.ok(fileMetaDataList);
    }

    @Operation(description = "파일 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(fileService.listFiles());
    }

    @Operation(description = "파일 다운로드")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) throws IOException {
        byte[] fileData = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileData);
    }

    @Operation(description = "파일 삭제")
    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> delete(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok("🗑️ Deleted: " + fileName);
    }
}
