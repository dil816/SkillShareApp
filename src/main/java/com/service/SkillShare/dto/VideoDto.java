package com.service.SkillShare.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class VideoDto {
    private String VideoTitle;
    private InputStream VideoStream;
}
