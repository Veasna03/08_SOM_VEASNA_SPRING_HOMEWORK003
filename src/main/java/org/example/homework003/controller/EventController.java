package org.example.homework003.controller;

import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Events;
import org.example.homework003.model.request.EventDTO;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Events>>> getAllEvent(@RequestParam(defaultValue = "10")Integer size,@RequestParam(defaultValue = "1")Integer page) {
        return ResponseEntity.ok(
                ApiResponse.<List<Events>>builder()
                        .message("Event with id is founded")
                        .payload(eventService.getAllEvents(size,page))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @GetMapping("/{event-id}")
     public ResponseEntity<ApiResponse<Events>> getEventById(@PathVariable("event-id") Integer eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Events>builder()
                        .message("Event with id is founded")
                        .payload(eventService.getEventById(eventId))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse> deleteEventById(@PathVariable("event-id") Integer eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Events>builder()
                        .message("Event with id is deleted")
                        .payload(eventService.deleteEventById(eventId))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createEvent(@RequestBody EventDTO event) {
       return ResponseEntity.status(HttpStatus.CREATED).body(
               ApiResponse.<Events>builder()
                       .message("Event created")
                       .payload(eventService.AddEvent(event))
                       .status(HttpStatus.CREATED)
                       .timestamp(LocalDateTime.now())
                       .build()
       );
    }
    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Events>> updateEventById(@PathVariable("event-id") Integer eventId, @RequestBody EventDTO event) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Events>builder()
                        .message("Event with id is updated")
                        .payload(eventService.updateEvent(eventId, event))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
