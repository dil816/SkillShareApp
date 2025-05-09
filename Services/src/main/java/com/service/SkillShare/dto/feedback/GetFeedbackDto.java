package com.service.SkillShare.dto.feedback;

import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Data
public class GetFeedbackDto {

    private String id;
    private String Comment;
    private String Author;
    private String CreatedAt;
    private String LikeCount;

}
