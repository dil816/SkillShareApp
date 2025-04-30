package com.service.SkillShare.mappings;

import com.service.SkillShare.dto.CreatePostDto;
import com.service.SkillShare.entity.Posts;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.io.IOException;
import java.time.LocalDateTime;

public class PostMapping {
//    public static Posts ToEntity(CreatePostDto createPostDto)  {
//        String contentType = createPostDto.getImgFile().getContentType();
//        Posts posts = new Posts();
//        if (contentType != null && contentType.startsWith("video")){
//
//        }else {
//            try {
//                posts.setTags(createPostDto.getTags());
//                posts.setContentTitle(createPostDto.getContentTitle());
//                posts.setPostDescription(createPostDto.getPostDescription());
//                posts.setPostImage(
//                        new Binary(BsonBinarySubType.BINARY, createPostDto.getImgFile().getBytes())
//                );
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        posts.setPublishedAt(LocalDateTime.now());
//        return posts;
//    }
}
