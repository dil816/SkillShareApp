package com.service.SkillShare.service;

import com.service.SkillShare.dto.Event.CreateEventDto;
import com.service.SkillShare.dto.Event.GetEventDto;
import com.service.SkillShare.dto.Event.UpdateEventDto;
import com.service.SkillShare.entity.Event;

import java.util.List;

public interface EventService {
    // Create a new Event Registration
    Event createEventRegistration(CreateEventDto eventDto);

    // Get all Event Registrations
    List<GetEventDto> getAllEventRegistrations();

    // Get Event Registration by ID
    GetEventDto getEventRegistrationById(String id);

    // Update Event Registration by ID
    Event updateEventRegistration(String id, UpdateEventDto updateEventDto);

    // Delete Event Registration by ID
    Boolean deleteEventRegistration(String id);
}
