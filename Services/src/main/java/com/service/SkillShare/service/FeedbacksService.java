package com.service.SkillShare.service;

import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.dto.post.CreatePostDto;
import com.service.SkillShare.dto.post.GetPostDto;
import com.service.SkillShare.dto.post.UpdatePostDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.entity.Posts;

import java.util.List;

public interface FeedbacksService {

    // Create a new Feedback
    Feedbacks CreateFeedback(CreateFeedbackDto createFeedbackDto);

    // Get All Feedback
    List<GetFeedbackDto> getAllFeedbacks();

    // Get Feedback By Id
    GetFeedbackDto getPostById(String id);

    // Update Feedback By Id
    Feedbacks updateFeedbacks(String id, UpdateFeedbackDto updateFeedbackDto);

    // Delete Feedback By Id
    Boolean deleteFeedbacks(String id);

}
