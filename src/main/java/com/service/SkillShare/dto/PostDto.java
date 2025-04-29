package com.service.SkillShare.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private MultipartFile ImgFile;  // photo or video
}


