package com.example.s3.s3test.dto;

import com.example.s3.s3test.domain.entity.FileEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String userId;
    private String email;
    private String originalFileName;
    private String filePath;
    private LocalDateTime createdDate;

    public FileEntity toEntity(){
        FileEntity build = FileEntity.builder()
                .id(id)
                .userId(userId)
                .email(email)
                .originalFileName(originalFileName)
                .filePath(filePath)
                .createdDate(createdDate)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String userId, String email, String originalFileName, String filePath, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.email=email;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.createdDate = createdDate;
    }
}