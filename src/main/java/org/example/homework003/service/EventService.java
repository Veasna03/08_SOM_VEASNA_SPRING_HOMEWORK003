package org.example.homework003.service;

import org.example.homework003.model.entity.Events;
import org.example.homework003.model.request.EventDTO;

import java.util.List;

public interface EventService {
    Events getEventById(Integer id);
    List<Events> getAllEvents();
    Events deleteEventById(Integer id);
    Events AddEvent(EventDTO event);
    Events updateEvent(Integer id,EventDTO event);
}
