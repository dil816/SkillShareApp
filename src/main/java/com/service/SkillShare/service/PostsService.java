package com.service.SkillShare.service;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.entity.Posts;

import java.util.List;

public interface PostsService {
    // Create a new Post
    Posts createPost(CreatePostDto createPostDto);

    // Get All Posts
    List<Posts> getAllPosts();
}
