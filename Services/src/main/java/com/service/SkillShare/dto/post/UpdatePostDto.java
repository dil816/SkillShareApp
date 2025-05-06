package com.service.SkillShare.dto.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdatePostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private MultipartFile MediaFile;  // photo or video
}
