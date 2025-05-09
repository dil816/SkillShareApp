package com.service.SkillShare.dto.feedback;

import lombok.Data;

import java.util.Date;
@Data
public class UpdateFeedbackDto {

    private String id;
    private String Comment;
    private String Author;
    private String CreatedAt;
    private String LikeCount;

}
