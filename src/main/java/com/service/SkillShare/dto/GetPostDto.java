package com.service.SkillShare.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetPostDto {
    private String id;
    private String Tags;
    private String ContentTitle;
    private String PostDescription;
    private String ImageBase64;

    private String ImageUrl;
    private String VideoUrl;


}
