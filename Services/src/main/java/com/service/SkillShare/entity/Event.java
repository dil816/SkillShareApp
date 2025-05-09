package com.service.SkillShare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event_registration")
public class Event {

    @Id
    private String id;

    private String eventName;

    private String description;

    private String createdAt;

    private String updatedAt;

    private String organizer;

    private String eventDate;

    private String location;
}
