package com.service.SkillShare.service;

import com.service.SkillShare.entity.Posts;
import java.util.List;

public interface PostsService {
    Posts createPost(Posts post);
    List<Posts> getAllPosts();
}
