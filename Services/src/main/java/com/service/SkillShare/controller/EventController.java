package com.service.SkillShare.controller;

import com.service.SkillShare.dto.Event.CreateEventDto;
import com.service.SkillShare.dto.Event.GetEventDto;
import com.service.SkillShare.dto.Event.UpdateEventDto;
import com.service.SkillShare.dto.feedback.CreateFeedbackDto;
import com.service.SkillShare.dto.feedback.GetFeedbackDto;
import com.service.SkillShare.dto.feedback.UpdateFeedbackDto;
import com.service.SkillShare.entity.Event;
import com.service.SkillShare.entity.Feedbacks;
import com.service.SkillShare.service.impl.EventServiceImpl;
import com.service.SkillShare.service.impl.FeedbacksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/event-registration")
public class EventController {
    private final EventServiceImpl eventService;

    EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @PostMapping("create")
    public ResponseEntity<Event> postEvent(@RequestBody CreateEventDto createFeedbackDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.createEventRegistration(createFeedbackDto));

    }

    @GetMapping("/")
    public ResponseEntity<List<GetEventDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEventRegistrations());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEventDto> geEventById(@PathVariable("id") String feedbackId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventRegistrationById(feedbackId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") String feedbackId,
            @RequestBody UpdateEventDto updateFeedbackDto) {
        var result = eventService.updateEventRegistration(feedbackId, updateFeedbackDto);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEvent(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(eventService.deleteEventRegistration(id));
    }
}
