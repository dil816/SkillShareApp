package com.service.SkillShare.repository;

import com.service.SkillShare.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event,String> {
}
