package org.example.homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.PushBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.request.AttendeeDTO;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.AttendeeService;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendee")
public class AttendeeController {
    private final AttendeeService attendeeService;
    private final ServletContextInitializer servletContextInitializer;

    @Operation(summary = "Get all attendee")
    @GetMapping
    ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Attendee>>builder()
                        .message("All attendees have been successfully fetched")
                        .payload(attendeeService.getAllAttendees(size, page))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @Operation(summary = "Get attendee by id ")
    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .message("Attendee with id " + attendeeId + " is founded")
                        .payload(attendeeService.getAttendeeByID(attendeeId))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }

    @Operation(summary = "Add attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@RequestBody AttendeeDTO attendee){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Attendee>builder()
                        .message("The attendee has been added successfully")
                        .payload(attendeeService.addAttendee(attendee))
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }@Operation(summary = "Update attendee by id")
    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAtttendeeById(@PathVariable("attendee-id")Integer id, @RequestBody AttendeeDTO attendeeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .message("Attendee with id " + id + " has been updated successfully")
                        .payload(attendeeService.updateAttendee(id, attendeeDTO))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Delete attendee by id")
    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleleAttendee(@PathVariable("attendee-id")Integer attendeeId, PushBuilder pushBuilder){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .message("The attendee has been deleted successfully")
                        .payload(attendeeService.deleteAttendee(attendeeId))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
//    @GetMapping("/{event-id}")
//    public ResponseEntity<ApiResponse<Attendee>> getAttendeeByEventId(@PathVariable("event-id")Integer eventId){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Attendee>builder()
//                        .message("Attendee with id " + eventId + " is founded")
//                        .payload(attendeeService.getAttendeeByEventId(eventId))
//                        .status(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//    @GetMapping("/{event-id}")
//    public Attendee getAttendeeByEventId(@PathVariable("event-id") Integer eventId){
//        return attendeeService.getAttendeeByEventId(eventId);
//    }
//



}