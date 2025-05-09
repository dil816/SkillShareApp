package com.service.SkillShare.dto.learningPlan;

import lombok.Data;

@Data
public class GetLearningPlansDto {
    private String id;
    private String title;
    private String category;
    private String description;
}
