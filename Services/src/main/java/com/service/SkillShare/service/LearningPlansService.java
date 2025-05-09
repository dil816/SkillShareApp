package com.service.SkillShare.service;

import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.dto.learningPlan.CreateLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.GetLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.UpdateLearningPlansDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.entity.LearningPlans;

import java.util.List;

public interface LearningPlansService {
    // Create a new Feedback
    LearningPlans CreateLearningPlans(CreateLearningPlansDto createLearningPlansDto);

    // Get All Feedback
    List<GetLearningPlansDto> getAllLearningPlans();

    // Get Feedback By Id
    GetLearningPlansDto getLearningPlansId(String id);

    // Update Feedback By Id
    LearningPlans updateLearningPlans(String id, UpdateLearningPlansDto updateLearningPlansDto);

    // Delete Feedback By Id
    Boolean deleteLearningPlans(String id);
}
