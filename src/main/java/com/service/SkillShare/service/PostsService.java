package com.service.SkillShare.service;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.dto.UpdatePostDto;
import com.service.SkillShare.dto.VideoDto;
import com.service.SkillShare.entity.Posts;

import java.io.IOException;
import java.util.List;

public interface PostsService {
    // Create a new Post
    Posts createPost(CreatePostDto createPostDto);

    // Get All Posts
    List<GetPostDto> getAllPosts();

    // Get Post By Id
    GetPostDto getPostById(String id);

    // Update Post By Id
    Posts updatePost(String id, UpdatePostDto updatePostDto);

    // Stream video
    VideoDto getVideo(String id);
}
