package com.service.SkillShare.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.mappings.PostMapping;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    PostsServiceImpl(PostsRepository postsRepository, GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.postsRepository = postsRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    @Override
    public Posts createPost(CreatePostDto createPostDto) {
        Posts posts = new Posts();
        //Posts posts = PostMapping.ToEntity(createPostDto);
        MultipartFile file = createPostDto.getImgFile();
        String contentType = createPostDto.getImgFile().getContentType();

        if (contentType != null && contentType.startsWith("video")) {
            try {
                DBObject metaData = new BasicDBObject();
                metaData.put("type", "video");
                metaData.put("title", "hghjg");

                posts.setPostVideoId(gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData));
                posts.setTags(createPostDto.getTags());
                posts.setContentTitle(createPostDto.getContentTitle());
                posts.setPostDescription(createPostDto.getPostDescription());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                posts.setTags(createPostDto.getTags());
                posts.setContentTitle(createPostDto.getContentTitle());
                posts.setPostDescription(createPostDto.getPostDescription());
                posts.setPostImage(
                        new Binary(BsonBinarySubType.BINARY, createPostDto.getImgFile().getBytes())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        posts.setPublishedAt(LocalDateTime.now());
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
