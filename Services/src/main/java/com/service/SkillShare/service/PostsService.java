package com.service.SkillShare.service;

import com.service.SkillShare.dto.post.CreatePostDto;
import com.service.SkillShare.dto.post.GetPostDto;
import com.service.SkillShare.dto.post.UpdatePostDto;
import com.service.SkillShare.dto.post.MediaDto;
import com.service.SkillShare.entity.Posts;

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

    // Delete Post By Id
    Boolean deletePost(String id);

    // Stream video
    MediaDto getMedia(String id);
}
