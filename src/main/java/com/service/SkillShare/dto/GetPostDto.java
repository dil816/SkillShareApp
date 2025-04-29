package com.service.SkillShare.dto;

import lombok.Data;

@Data
public class GetPostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private String ImageBase64;
}
