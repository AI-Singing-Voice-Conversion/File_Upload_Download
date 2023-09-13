package com.example.s3.s3test.domain.repository;

import com.example.s3.s3test.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    @Query("SELECT file FROM FileEntity file WHERE file.userId = :userId")
    List<FileEntity> findAllByUserId(@Param("userId") String userId);
}