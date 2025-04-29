package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.mappings.PostMapping;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
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
    public List<GetPostDto> getAllPosts() {
        List<Posts> posts = postsRepository.findAll();
        if (posts.isEmpty()) {
            return new ArrayList<>();
        }

//        GetPostDto data = posts.forEach(post -> {
//            GetPostDto postDto = new GetPostDto();
//            postDto.setId(post.getId());
//            postDto.setTags(post.getTags());
//            postDto.setContentTitle(post.getContentTitle());
//            postDto.setPostDescription(post.getPostDescription());
//            postDto.setImageBase64(
//                    Base64.getEncoder().encodeToString(
//                            post.getPostImage().getData()
//                    )
//            );
//        });

        List<GetPostDto> data = posts.stream()
                .map(post -> {
                    GetPostDto postDto = new GetPostDto();
                    postDto.setId(post.getId());
                    postDto.setTags(post.getTags());
                    postDto.setContentTitle(post.getContentTitle());
                    postDto.setPostDescription(post.getPostDescription());
                    postDto.setImageBase64(
                            Base64.getEncoder().encodeToString(
                                    post.getPostImage().getData()
                            )
                    );
                    return postDto;
                })
                .toList();

        return data;
    }
}
