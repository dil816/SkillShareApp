package com.service.SkillShare.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetPostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private String ImageUrl;
    private String VideoUrl;
}
