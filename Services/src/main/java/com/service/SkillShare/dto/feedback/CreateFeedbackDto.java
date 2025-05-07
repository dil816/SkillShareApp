package com.service.SkillShare.dto.feedback;

import lombok.Data;

import java.util.Date;

@Data

public class CreateFeedbackDto {

    private String id;
    private String Comment;
    private String Author;
    private Date CreatedAt;
    private String LikeCount;

}
