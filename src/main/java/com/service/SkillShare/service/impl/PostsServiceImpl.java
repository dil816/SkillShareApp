package com.service.SkillShare.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.dto.GetPostDto;
import com.service.SkillShare.dto.UpdatePostDto;
import com.service.SkillShare.dto.MediaDto;
import com.service.SkillShare.entity.Posts;
import com.service.SkillShare.repository.PostsRepository;
import com.service.SkillShare.service.PostsService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


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
        MultipartFile file = createPostDto.getImgFile();
        String contentType = createPostDto.getImgFile().getContentType();
        UpsertPostWithImageVideo(contentType, posts, file, createPostDto.getTags(), createPostDto.getContentTitle(), createPostDto.getPostDescription());
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
                    if (post.getPostImageId() != null) {
                        postDto.setImageUrl(videoControllerUrl + post.getPostImageId());
                    }
                    if (post.getPostVideoId() != null) {
                        postDto.setVideoUrl(videoControllerUrl + post.getPostVideoId());
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
            if (post.get().getPostImageId() != null) {
                postDto.setImageUrl(videoControllerUrl + post.get().getPostImageId());
            }
            if (post.get().getPostVideoId() != null) {
                postDto.setVideoUrl(videoControllerUrl + post.get().getPostVideoId());
            }
            return postDto;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Posts updatePost(String id, UpdatePostDto updatePostDto) {
        Posts existingPost = postsRepository.findById(id).orElse(null);
        MultipartFile file = updatePostDto.getImgFile();
        String contentType = updatePostDto.getImgFile().getContentType();
        // if post have
        if (existingPost != null) {
            if (contentType != null && contentType.startsWith("video")) {
                //delete existing gridFs file (video)
                gridFsTemplate.delete(Query.query(Criteria.where("_id").is(existingPost.getPostVideoId())));
            } else if (contentType != null && contentType.startsWith("image")) {
                //delete existing gridFs file (image)
                gridFsTemplate.delete(Query.query(Criteria.where("_id").is(existingPost.getPostImageId())));
            }
            UpsertPostWithImageVideo(contentType, existingPost, file, updatePostDto.getTags(), updatePostDto.getContentTitle(), updatePostDto.getPostDescription());
            existingPost.setUpdatedAt(LocalDateTime.now());
            postsRepository.save(existingPost);
            return existingPost;
        }
        return null;
    }

    @Override
    public Boolean deletePost(String id) {
        Posts postToDelete = postsRepository.findById(id).orElse(null);
        if (postToDelete == null) {
            return false;
        }
        if (postToDelete.getPostVideoId() != null) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(postToDelete.getPostVideoId())));
        }
        postsRepository.delete(postToDelete);
        return true;
    }

    private void UpsertPostWithImageVideo(String contentType, Posts post, MultipartFile file, String tags, String contentTitle, String postDescription) {
        try {
            System.out.println(contentType);
            if (contentType != null && contentType.startsWith("video")) {
                DBObject metaData = new BasicDBObject();
                metaData.put("type", "video");
                metaData.put("title", UUID.randomUUID().toString());
                post.setPostVideoId(gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData));
            } else if (contentType != null && contentType.startsWith("image")) {
                DBObject metaData = new BasicDBObject();
                metaData.put("type", "image");
                metaData.put("title", UUID.randomUUID().toString());
                post.setPostImageId(gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData));
            }
            post.setTags(tags);
            post.setContentTitle(contentTitle);
            post.setPostDescription(postDescription);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MediaDto getMedia(String id) {
        MediaDto mediaDto = new MediaDto();
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        if (file.getMetadata() != null) {
            mediaDto.setMediaTitle(file.getMetadata().get("title").toString());
        }
        try {
            mediaDto.setMediaStream(operations.getResource(file).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mediaDto;
    }
}
