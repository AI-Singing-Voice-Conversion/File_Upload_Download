package com.example.s3.s3test.controller;

import com.example.s3.s3test.dto.FileDto;
import com.example.s3.s3test.service.FileService;
import com.example.s3.s3test.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@AllArgsConstructor
public class FileController {
    private S3Service s3Service;
    private FileService fileService;

    @GetMapping("/svc")
    public String dispWrite() {

        return "file";
    }

    @PostMapping("/svc")
    public String execWrite(FileDto fileDto, MultipartFile file,
                            @RequestParam("userId") String userId, @RequestParam("email") String email) throws IOException {

        String filePath = s3Service.upload(file);
        fileDto.setFilePath(filePath);
        fileDto.setUserId(userId);
        fileDto.setEmail(email);
        String originalFileName = s3Service.findOriginalFileName(file);
        fileDto.setOriginalFileName(originalFileName);
        LocalDateTime createdDate = s3Service.loadDate(file);
        fileDto.setCreatedDate(createdDate);
        fileService.savePost(fileDto);
        System.out.println(fileDto.getUserId());
        System.out.println(fileDto.getEmail());
        System.out.println(fileDto.getOriginalFileName());
        System.out.println(fileDto.getFilePath());
        System.out.println(fileDto.getCreatedDate());
        return "redirect:/svc";
    }

    @GetMapping("/svc/search")
    public String dispUserFilesByUserId() {
        return "fileSearch";
    }


    @PostMapping("/svc/search")
    public String execUserFileSearch(@RequestParam("userId") String userId, Model model) {
        List<FileDto> userFiles = fileService.getUserFilesByUserId(userId);
        model.addAttribute("userFiles", userFiles);
        return "user-files";
    }


}