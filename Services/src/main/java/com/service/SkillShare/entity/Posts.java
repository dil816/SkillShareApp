package com.service.SkillShare.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Posts {
    @Id
    private String id;
    private String Tags;
    private String ContentTitle;
    private ObjectId PostVideoId;
    private ObjectId PostImageId;
    private String PostDescription;
    private LocalDateTime PublishedAt;
    private LocalDateTime UpdatedAt;
}
