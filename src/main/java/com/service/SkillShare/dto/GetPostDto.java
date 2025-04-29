package com.service.SkillShare.dto;

import org.springframework.web.multipart.MultipartFile;

public class GetPostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private MultipartFile ImageBase64;
}
