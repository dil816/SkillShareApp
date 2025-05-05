package com.service.SkillShare.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.dto.UpdatePostDto;
import com.service.SkillShare.dto.VideoDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


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
        String videoControllerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/posts/videos/stream/").toUriString();
        List<Posts> posts = postsRepository.findAll();
        if (posts.isEmpty()) {
            return new ArrayList<>();
        }
        List<GetPostDto> data = posts.stream()
                .map(post -> {
                    GetPostDto postDto = new GetPostDto();
                    postDto.setId(post.getId());
                    postDto.setTags(post.getTags());
                    postDto.setContentTitle(post.getContentTitle());
                    postDto.setPostDescription(post.getPostDescription());
                    if (post.getPostImage() != null) {
                        postDto.setImageBase64(
                                Base64.getEncoder().encodeToString(
                                        post.getPostImage().getData()
                                )
                        );
                    }
                    if (post.getPostVideoId() != null) {
                        postDto.setVideoUrl(videoControllerUrl + post.getPostVideoId().toString());
                    }
                    return postDto;
                })
                .toList();

        return data;
    }

    @Override
    public GetPostDto getPostById(String id) {
        String videoControllerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/posts/videos/stream/").toUriString();
        Optional<Posts> post = postsRepository.findById(id);
        GetPostDto postDto = new GetPostDto();
        if (post.isPresent()) {
            postDto.setId(post.get().getId());
            postDto.setTags(post.get().getTags());
            postDto.setContentTitle(post.get().getContentTitle());
            postDto.setPostDescription(post.get().getPostDescription());
            if (post.get().getPostImage() != null) {
                postDto.setImageBase64(
                        Base64.getEncoder().encodeToString(
                                post.get().getPostImage().getData()
                        )
                );
            }
            if (post.get().getPostVideoId() != null) {
                postDto.setVideoUrl(videoControllerUrl + post.get().getPostVideoId().toString());
            }
            return postDto;
        }else {
          throw new RuntimeException();
        }
    }

    @Override
    public Posts updatePost(String id, UpdatePostDto updatePostDto) {
        Posts existingPost = postsRepository.findById(id).orElse(null);
        MultipartFile file = updatePostDto.getImgFile();
        String contentType = updatePostDto.getImgFile().getContentType();
        // if post have
        if(existingPost!=null) {
            if (contentType != null && contentType.startsWith("video")) {
                //delete existing gridFs file
                gridFsTemplate.delete(Query.query(Criteria.where("_id").is(existingPost.getPostVideoId())));
                try {
                    DBObject metaData = new BasicDBObject();
                    metaData.put("type", "video");
                    metaData.put("title", "hghjg");

                    existingPost.setPostVideoId(gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData));
                    existingPost.setTags(updatePostDto.getTags());
                    existingPost.setContentTitle(updatePostDto.getContentTitle());
                    existingPost.setPostDescription(updatePostDto.getPostDescription());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    existingPost.setTags(updatePostDto.getTags());
                    existingPost.setContentTitle(updatePostDto.getContentTitle());
                    existingPost.setPostDescription(updatePostDto.getPostDescription());
                    existingPost.setPostImage(
                            new Binary(BsonBinarySubType.BINARY, updatePostDto.getImgFile().getBytes())
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            existingPost.setUpdatedAt(LocalDateTime.now());
            postsRepository.save(existingPost);
            return existingPost;
        }


        return null;
    }

    @Override
    public VideoDto getVideo(String id) {
        VideoDto videoDto = new VideoDto();
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        if (file.getMetadata() != null) {
            videoDto.setVideoTitle(file.getMetadata().get("title").toString());
        }
        try {
            videoDto.setVideoStream(operations.getResource(file).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return videoDto;
    }
}
