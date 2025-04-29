package com.service.SkillShare.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Posts {
    @Id
    private String id;
    private String tags;
    private String ContentTitle;
    private Binary PostImage;
    private InputStream PostVideo;
    private String PostDescription;
    private LocalDateTime PublishedAt;

    public  void  setPublishedAt() {
        this.PublishedAt = LocalDateTime.now();
    }
}
