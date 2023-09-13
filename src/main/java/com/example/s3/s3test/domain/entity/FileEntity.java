package com.example.s3.s3test.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@Table(name="file")
@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String userId;

    @Column(length = 50)
    private String email;

    @Column(length = 50, nullable = false)
    private String originalFileName;

    @Column(/*columnDefinition = "TEXT", */nullable = false)
    private String filePath;

    //@CreatedDate
    @Column(updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm:ss"/*, timezone = "Asia/Seoul"*/)
    private LocalDateTime createdDate;

}