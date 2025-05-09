package com.service.SkillShare.dto.Event;

import lombok.Data;

@Data
public class CreateEventDto {
    private String id;
    private String eventName;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String organizer;
    private String eventDate;
    private String location;
}
