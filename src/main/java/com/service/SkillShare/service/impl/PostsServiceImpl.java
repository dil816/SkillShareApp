package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.PostDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.mappings.PostMapping;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Posts createPost(PostDto postDto) {
        Posts posts = PostMapping.ToEntity(postDto);
        return postsRepository.insert(posts);
    }

    @Override
    public List<Posts> getAllPosts() {
        return List.of();
    }
}
