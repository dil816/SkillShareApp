package com.service.SkillShare.controller;

import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.dto.learningPlan.CreateLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.GetLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.UpdateLearningPlansDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.entity.LearningPlans;
import com.service.SkillShare.service.LearningPlansService;
import com.service.SkillShare.service.impl.LearningPlansServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/learningplans")
public class LearningPlansController {
    private final LearningPlansServiceImpl learningPlansService;

    LearningPlansController(LearningPlansServiceImpl learningPlansService){
        this.learningPlansService = learningPlansService;
    }

    @PostMapping("create")
    public ResponseEntity<LearningPlans> postLearningPlans(@RequestBody CreateLearningPlansDto createLearningPlansDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(learningPlansService.CreateLearningPlans(createLearningPlansDto));

    }

    @GetMapping()
    public ResponseEntity<List<GetLearningPlansDto>> getAllLearningPlans() {
        return ResponseEntity.status(HttpStatus.OK).body(learningPlansService.getAllLearningPlans());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetLearningPlansDto> getLearningPlansById(@PathVariable("id") String lerningplane) {
        return ResponseEntity.status(HttpStatus.OK).body(learningPlansService.getLearningPlansId(lerningplane));
    }


    @PutMapping("{id}")
    public ResponseEntity<LearningPlans> updateLearningPlans(@PathVariable("id") String lerningplane, @RequestBody UpdateLearningPlansDto updateLearningPlansDto)
    {
        var result = learningPlansService.updateLearningPlans(lerningplane, updateLearningPlansDto);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLearningPlans(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(learningPlansService.deleteLearningPlans(id));
    }
}
