package com.service.SkillShare.dto.learningPlan;

import lombok.Data;

@Data
public class CreateLearningPlansDto {
    private String id;
    private String title;
    private String category;
    private String description;
}
