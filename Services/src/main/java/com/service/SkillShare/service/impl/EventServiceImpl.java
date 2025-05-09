package com.service.SkillShare.service.impl;

import com.service.SkillShare.dto.Event.CreateEventDto;
import com.service.SkillShare.dto.Event.GetEventDto;
import com.service.SkillShare.dto.Event.UpdateEventDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.entity.Event;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.exception.NotFoundException;
import com.service.SkillShare.repository.EventRepository;
import com.service.SkillShare.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEventRegistration(CreateEventDto eventDto) {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setDescription(eventDto.getDescription());
        event.setCreatedAt(eventDto.getCreatedAt());
        event.setUpdatedAt(eventDto.getUpdatedAt());
        event.setOrganizer(eventDto.getOrganizer());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());
        return eventRepository.insert(event);
    }

    @Override
    public List<GetEventDto> getAllEventRegistrations() {
        List<Event> event = eventRepository.findAll();
        if(event.isEmpty()){
            return new ArrayList<>();
        }
        List<GetEventDto> data = event.stream()
                .map(e ->{
                    GetEventDto getEventDto = new GetEventDto();
                    getEventDto.setId(e.getId());
                    getEventDto.setEventName(e.getEventName());
                    getEventDto.setDescription(e.getDescription());
                    getEventDto.setCreatedAt(e.getCreatedAt());
                    getEventDto.setUpdatedAt(e.getUpdatedAt());
                    getEventDto.setOrganizer(e.getOrganizer());
                    getEventDto.setEventDate(e.getEventDate());
                    getEventDto.setLocation(e.getLocation());
                    return getEventDto;
                })
                .toList();
        return data;
    }

    @Override
    public GetEventDto getEventRegistrationById(String id) {
        Optional<Event> event = eventRepository.findById(id);
        GetEventDto eventDto = new GetEventDto();
        if(event.isPresent()){
            eventDto.setId(event.get().getId());
            eventDto.setEventName(event.get().getEventName());
            eventDto.setDescription(event.get().getDescription());
            eventDto.setCreatedAt(event.get().getCreatedAt());
            eventDto.setUpdatedAt(event.get().getUpdatedAt());
            eventDto.setOrganizer(event.get().getOrganizer());
            eventDto.setEventDate(event.get().getEventDate());
            eventDto.setLocation(event.get().getLocation());

            return eventDto;
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public Event updateEventRegistration(String id, UpdateEventDto updateEventDto) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if(existingEvent != null){
            existingEvent.setEventName(updateEventDto.getEventName());
            existingEvent.setDescription(updateEventDto.getDescription());
            existingEvent.setCreatedAt(updateEventDto.getCreatedAt());
            existingEvent.setUpdatedAt(updateEventDto.getUpdatedAt());
            existingEvent.setOrganizer(updateEventDto.getOrganizer());
            existingEvent.setEventDate(updateEventDto.getEventDate());
            existingEvent.setLocation(updateEventDto.getLocation());

            eventRepository.save(existingEvent);
            return existingEvent;
        }
        return null;
    }

    @Override
    public Boolean deleteEventRegistration(String id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Feedback not found with ID: " + id);
        }
        eventRepository.deleteById(id);
        return true;
    }
}
