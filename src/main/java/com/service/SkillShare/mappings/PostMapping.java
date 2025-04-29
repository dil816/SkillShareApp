package com.service.SkillShare.mappings;

import com.service.SkillShare.dto.PostDto;
import com.service.SkillShare.entity.Posts;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.io.IOException;
import java.time.LocalDateTime;

public class PostMapping {
    public static Posts ToEntity(PostDto postDto)  {
        Posts posts = new Posts();
        try {
            posts.setTags(postDto.getTags());
            posts.setContentTitle(postDto.getContentTitle());
            posts.setPostDescription(postDto.getPostDescription());
            posts.setPostImage(
                    new Binary(BsonBinarySubType.BINARY, postDto.getFile().getBytes())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        posts.setPublishedAt(LocalDateTime.now());
        return posts;
    }
}
