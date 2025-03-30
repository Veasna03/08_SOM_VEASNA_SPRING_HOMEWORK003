package org.example.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.homework003.model.entity.Events;
import org.example.homework003.model.request.EventDTO;
import org.example.homework003.repository.AttendeeRepository;
import org.example.homework003.repository.EventRepository;
import org.example.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServceImpl implements EventService {
    private final AttendeeRepository attendeeRepository;
    private final EventRepository eventRepository;
    @Override
    public Events getEventById(Integer id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvent();
    }

    @Override
    public Events deleteEventById(Integer id) {
        return eventRepository.deleteEventById(id);
    }

    @Override
    public Events AddEvent(EventDTO event) {
         Events events= eventRepository.insertEvent(event);
         attendeeRepository.insertEventAndAttendee(events.getEventId(),event.getVenueId());
        return eventRepository.getEventById(events.getEventId());
    }

    @Override
    public Events updateEvent(Integer id, EventDTO event) {
       Events events= eventRepository.updateEventAttendee(id,event);
       eventRepository.deleteEventAttendeeByEventId(events.getEventId());
       attendeeRepository.insertEventAndAttendee(events.getEventId(),event.getAttendeeId());
        return eventRepository.updateEventAttendee(events.getEventId(),event);
    }
}
