package org.example.homework003.repository;

import jakarta.websocket.server.ServerEndpoint;
import jdk.jfr.Event;
import org.apache.ibatis.annotations.*;
import org.example.homework003.model.entity.Events;
import org.example.homework003.model.request.EventDTO;

import java.util.List;

@Mapper
public interface EventRepository {




    @Select("""
     select * from events ;

""")
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId",column = "event_id"),
            @Result(property = "eventName",column = "event_name"),
            @Result(property = "eventDate",column = "event_date"),
            @Result(property = "venue"
                    ,column = "venue_id",
                    one = @One(select= "org.example.homework003.repository.VenuesRepository.getvenueById")

            ),
            @Result(property ="attendee",column = "event_id",
                    many = @Many(select = "org.example.homework003.repository.AttendeeRepository.getAttendeeByEventId")
            )
    })
    List<Events> getAllEvent();

//    @Select("""
//SELECT
//    a.attendee_id,
//    a.attendee_name,
//    a.email,
//    e.event_id,
//    e.event_name,
//    e.event_date
//FROM event_attendee ea
//         INNER JOIN events e ON ea.event_id = e.event_id
//         INNER JOIN attendees a ON ea.attendee_id = a.attendee_id
//WHERE e.event_id=#{id};
//""")


    @Select("""
    select * from events where event_id=#{id};
""")
    @ResultMap("eventMapper")
    Events getEventById(Integer id);


    @Select("""
delete from events where event_id=#{id} RETURNING *;
""")
    @ResultMap("eventMapper")
    Events deleteEventById(Integer id);


    @Select("""
insert into  events (event_name,event_date,venue_id) values (#{event.eventName},#{event.eventDate},#{event.venueId}) RETURNING *;
""")
    @ResultMap("eventMapper")
    Events insertEvent(@Param("event") EventDTO event);

     @Select("""
      delete from event_attendee where event_id=#{id}
""")
    void deleteEventAttendeeByEventId(Integer id);


     @Select("""
      update events set event_name=#{event.eventName},event_date=#{event.eventDate},venue_id=#{event.venueId} where event_id=#{id} RETURNING *;
""")
     @ResultMap("eventMapper")
    Events updateEventAttendee(Integer id,@Param("event") EventDTO event);




}
