package com.service.SkillShare.service;

import com.service.SkillShare.dto.PostDto;
import com.service.SkillShare.entity.Posts;

import java.io.IOException;
import java.util.List;

public interface PostsService {
    // Create a new Post
    Posts createPost(PostDto postDto);

    // Get All Posts
    List<Posts> getAllPosts();
}
