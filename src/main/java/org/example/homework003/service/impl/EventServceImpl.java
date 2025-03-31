package org.example.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.homework003.exception.NullPointerExceptino;
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
        if(eventRepository.getEventById(id) == null) {
           // throw new NullPointerExceptino("Event with id " + id + " not found");
        }
        return eventRepository.getEventById(id);
    }

    @Override
    public List<Events> getAllEvents(Integer size,Integer page) {
        return eventRepository.getAllEvent(size,page);
    }

    @Override
    public Events deleteEventById(Integer id) {
        if(eventRepository.deleteEventById(id) == null) {
           // throw new NullPointerExceptino("Event with id " + id + " not found");
        }

        return eventRepository.deleteEventById(id);
    }

    @Override
    public Events AddEvent(EventDTO event) {
         Events events= eventRepository.insertEvent(event);
         for(Integer attendeeId:event.getAttendeeId()){
             attendeeRepository.insertEventAndAttendee(events.getEventId(),attendeeId);
         }

        return eventRepository.getEventById(events.getEventId());
    }

    @Override
    public Events updateEvent(Integer id, EventDTO event) {
//        if(eventRepository.getEventById(id) == null) {
//            throw new NullPointerExceptino("Event with id " + id + " not found");
//        }
       Events events= eventRepository.updateEventAttendee(id,event);
       eventRepository.deleteEventAttendeeByEventId(events.getEventId());
       for (Integer attendeeId:event.getAttendeeId()){
           attendeeRepository.insertEventAndAttendee(events.getEventId(),attendeeId);
       }

        return eventRepository.updateEventAttendee(events.getEventId(),event);
    }
}
