package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.learningPlan.CreateLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.GetLearningPlansDto;
import com.service.SkillShare.dto.learningPlan.UpdateLearningPlansDto;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.entity.LearningPlans;
import com.service.SkillShare.exception.NotFoundException;
import com.service.SkillShare.repository.FeedbacksRepository;
import com.service.SkillShare.repository.LearningPlansRepository;
import com.service.SkillShare.service.LearningPlansService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LearningPlansServiceImpl implements LearningPlansService {
    private final LearningPlansRepository learningPlansRepository;

    LearningPlansServiceImpl(LearningPlansRepository learningPlansRepository){
        this.learningPlansRepository = learningPlansRepository;
    }
    @Override
    public LearningPlans CreateLearningPlans(CreateLearningPlansDto createLearningPlansDto) {
        LearningPlans learningPlans = new LearningPlans();
        learningPlans.setTitle(createLearningPlansDto.getTitle());
        learningPlans.setCategory(createLearningPlansDto.getCategory());
        learningPlans.setDescription(createLearningPlansDto.getDescription());
        return learningPlansRepository.insert(learningPlans);
    }

    @Override
    public List<GetLearningPlansDto> getAllLearningPlans() {
        List<LearningPlans> learningPlans = learningPlansRepository.findAll();
        if(learningPlans.isEmpty()){
            return new ArrayList<>();
        }
        List<GetLearningPlansDto> data = learningPlans.stream()
                .map(plans ->{
                    GetLearningPlansDto learningPlansDto = new GetLearningPlansDto();
                    learningPlansDto.setId(plans.getId());
                    learningPlansDto.setTitle(plans.getTitle());
                    learningPlansDto.setDescription(plans.getDescription());
                    learningPlansDto.setCategory(plans.getCategory());
                    return learningPlansDto;
                })
                .toList();
        return data;
    }

    @Override
    public GetLearningPlansDto getLearningPlansId(String id) {
        Optional<LearningPlans> learningPlans = learningPlansRepository.findById(id);
        GetLearningPlansDto learningPlansDto = new GetLearningPlansDto();
        if(learningPlans.isPresent()){
            learningPlansDto.setId(learningPlans.get().getId());
            learningPlansDto.setTitle(learningPlans.get().getTitle());
            learningPlansDto.setDescription(learningPlans.get().getDescription());
            learningPlansDto.setCategory(learningPlans.get().getCategory());

            return learningPlansDto;
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public LearningPlans updateLearningPlans(String id, UpdateLearningPlansDto updateLearningPlansDto) {
        LearningPlans existingLearningPlans = learningPlansRepository.findById(id).orElse(null);
        if(existingLearningPlans != null){
            existingLearningPlans.setTitle(updateLearningPlansDto.getTitle());
            existingLearningPlans.setDescription(updateLearningPlansDto.getDescription());
            existingLearningPlans.setCategory(updateLearningPlansDto.getCategory());
            learningPlansRepository.save(existingLearningPlans);
            return existingLearningPlans;
        }
        return null;
    }

    @Override
    public Boolean deleteLearningPlans(String id) {
        if (!learningPlansRepository.existsById(id)) {
            throw new NotFoundException("Feedback not found with ID: " + id);
        }
        learningPlansRepository.deleteById(id);
        return true;
    }
}
