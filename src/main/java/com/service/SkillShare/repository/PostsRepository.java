package com.service.SkillShare.repository;

import com.service.SkillShare.entity.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Posts, String> {
}
