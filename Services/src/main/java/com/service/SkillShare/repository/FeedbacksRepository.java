package com.service.SkillShare.repository;

import com.service.SkillShare.entity.Feedbacks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbacksRepository extends MongoRepository<Feedbacks, String> {
}
