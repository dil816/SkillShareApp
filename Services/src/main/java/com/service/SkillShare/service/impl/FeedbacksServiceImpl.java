package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.exception.NotFoundException;
import com.service.SkillShare.repository.FeedbacksRepository;
import com.service.SkillShare.service.FeedbacksService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbacksServiceImpl implements FeedbacksService {

    private final FeedbacksRepository feedbacksRepository;

    FeedbacksServiceImpl (FeedbacksRepository feedbacksRepository){
        this.feedbacksRepository = feedbacksRepository;
    }

    @Override
    public Feedbacks CreateFeedback(CreateFeedbackDto createFeedbackDto) {
        Feedbacks feedbacks = new Feedbacks();
        feedbacks.setComment(createFeedbackDto.getComment());
        feedbacks.setAuthor(createFeedbackDto.getAuthor());
        feedbacks.setCreatedAt(createFeedbackDto.getCreatedAt());
        feedbacks.setLikeCount(createFeedbackDto.getLikeCount());
        return feedbacksRepository.insert(feedbacks);
    }

    @Override
    public List<GetFeedbackDto> getAllFeedbacks() {
        List<Feedbacks> feedbacks = feedbacksRepository.findAll();
        if(feedbacks.isEmpty()){
            return new ArrayList<>();
        }
        List<GetFeedbackDto> data = feedbacks.stream()
                .map(feedback ->{
                    GetFeedbackDto feedbackDto = new GetFeedbackDto();
                    feedbackDto.setId(feedback.getId());
                    feedbackDto.setComment(feedback.getComment());
                    feedbackDto.setAuthor(feedback.getAuthor());
                    feedbackDto.setLikeCount(feedback.getLikeCount());
                    feedbackDto.setCreatedAt(feedback.getCreatedAt());
                    return feedbackDto;
                })
                .toList();
        return data;
    }

    @Override
    public GetFeedbackDto getFeedbackById(String id) {

        Optional<Feedbacks> feedback = feedbacksRepository.findById(id);
        GetFeedbackDto feedbackDto = new GetFeedbackDto();
        if(feedback.isPresent()){
            feedbackDto.setId(feedback.get().getId());
            feedbackDto.setComment(feedback.get().getComment());
            feedbackDto.setAuthor(feedback.get().getAuthor());
            feedbackDto.setLikeCount(feedback.get().getLikeCount());
            feedbackDto.setCreatedAt(feedback.get().getCreatedAt());

            return feedbackDto;
        }else{
            throw new RuntimeException();
        }

    }

    @Override
    public Feedbacks updateFeedbacks(String id, UpdateFeedbackDto updateFeedbackDto) {
        Feedbacks existingFeedback = feedbacksRepository.findById(id).orElse(null);
        if(existingFeedback != null){
            existingFeedback.setLikeCount(updateFeedbackDto.getLikeCount());
            existingFeedback.setAuthor(updateFeedbackDto.getAuthor());
            existingFeedback.setCreatedAt(updateFeedbackDto.getCreatedAt());
            existingFeedback.setComment(updateFeedbackDto.getComment());
            feedbacksRepository.save(existingFeedback);
            return existingFeedback;
        }
        return null;
    }

    @Override
    public Boolean deleteFeedbacks(String id) {
        if (!feedbacksRepository.existsById(id)) {
            throw new NotFoundException("Feedback not found with ID: " + id);
        }
        feedbacksRepository.deleteById(id);
        return true;
    }


}

