package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.mappings.PostMapping;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Posts createPost(CreatePostDto createPostDto) {
        Posts posts = PostMapping.ToEntity(createPostDto);
        return postsRepository.insert(posts);
    }

    @Override
    public List<Posts> getAllPosts() {
        return List.of();
    }
}
