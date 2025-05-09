package com.service.SkillShare.dto.learningPlan;

import lombok.Data;

@Data
public class UpdateLearningPlansDto {
    private String id;
    private String title;
    private String category;
    private String description;
}
