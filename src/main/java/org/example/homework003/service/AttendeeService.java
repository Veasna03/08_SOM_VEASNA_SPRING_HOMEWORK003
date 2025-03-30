package org.example.homework003.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.request.AttendeeDTO;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer size, Integer page);
    Attendee getAttendeeByID(Integer id) throws NotFoundException;
    Attendee addAttendee(AttendeeDTO attendeeDTO);
    Attendee updateAttendee(Integer id, AttendeeDTO attendeeDTO);
    Attendee deleteAttendee(Integer id);
    Attendee getAttendeeByEventId(Integer eventId);





}
