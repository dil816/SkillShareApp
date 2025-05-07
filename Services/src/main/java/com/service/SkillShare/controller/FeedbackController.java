package com.service.SkillShare.controller;

import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.dto.post.GetPostDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.repository.FeedbacksRepository;
import com.service.SkillShare.service.FeedbacksService;
import com.service.SkillShare.service.impl.FeedbacksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FeedbackController {


    private final FeedbacksServiceImpl feedbacksService;

    FeedbackController(FeedbacksServiceImpl feedbacksService) {this.feedbacksService = feedbacksService;}

    public ResponseEntity<Feedbacks> postfeedback( @RequestBody CreateFeedbackDto createFeedbackDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedbacksService.CreateFeedback(createFeedbackDto));

    }

    @GetMapping()
    public ResponseEntity<List<GetFeedbackDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(feedbacksService.getAllFeedbacks());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetFeedbackDto> getPostById(@PathVariable("id") String feedbackId) {
        return ResponseEntity.status(HttpStatus.OK).body(feedbacksService.getFeedbackById(feedbackId));
    }


    @PutMapping("{id}")
    public ResponseEntity<Feedbacks> updateFeedback(@PathVariable("id") String feedbackId, @RequestBody UpdateFeedbackDto updateFeedbackDto)
    {
        var result = feedbacksService.updateFeedbacks(feedbackId, updateFeedbackDto);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") String feedbackId)
    {
        if (feedbacksService.deleteFeedbacks(feedbackId)) {
            return new ResponseEntity<>("Post deleted Successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post deleted Successfully.", HttpStatus.NOT_FOUND);
        }
    }

}
