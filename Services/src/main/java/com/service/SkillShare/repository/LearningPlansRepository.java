package com.service.SkillShare.repository;

import com.service.SkillShare.entity.LearningPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearningPlansRepository extends MongoRepository<LearningPlans,String> {
}
