package com.service.SkillShare.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class MediaDto {
    private String MediaTitle;
    private InputStream MediaStream;
}
