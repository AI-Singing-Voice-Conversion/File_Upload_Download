package com.example.s3.s3test.service;

import com.example.s3.s3test.domain.entity.FileEntity;
import com.example.s3.s3test.domain.repository.FileRepository;
import com.example.s3.s3test.dto.FileDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    public void savePost(FileDto fileDto) {
        fileRepository.save(fileDto.toEntity());
    }


    public List<FileDto> getUserFilesByUserId(String userId) {
        System.out.println("User ID: " + userId);
        List<FileEntity> fileEntities = fileRepository.findAllByUserId(userId);
        return convertToDtoList(fileEntities);
    }

    private List<FileDto> convertToDtoList(List<FileEntity> fileEntities) {
        List<FileDto> fileDtos = new ArrayList<>();

        for (FileEntity fileEntity : fileEntities) {
            FileDto fileDto = new FileDto();
            fileDto.setEmail(fileEntity.getEmail());
            fileDto.setUserId(fileEntity.getUserId());
            fileDto.setOriginalFileName(fileEntity.getOriginalFileName());
            fileDto.setCreatedDate(fileEntity.getCreatedDate());
            fileDto.setFilePath(fileEntity.getFilePath());

            fileDtos.add(fileDto);
        }

        return fileDtos;
    }


}
