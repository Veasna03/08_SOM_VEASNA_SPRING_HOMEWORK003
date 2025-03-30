package org.example.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.request.AttendeeDTO;
import org.example.homework003.service.AttendeeService;

import java.util.List;

@Mapper
public interface AttendeeRepository {


    @Select("""
select * from attendees  
       offset #{size} *(#{page}-1)
  limit #{size} ;
""")
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    List<Attendee> getAllAttendees(Integer size, Integer page);



    @Select("""

  select  * from attendees where attendee_id=#{id}

""")
    @ResultMap("attendeeMapper")
    Attendee getAttendeeById(Integer id);

    //++++++++++++++++Add+++++++++++++
    @Select("""
insert into attendees ( attendee_name,email) values (#{attendee.attendeeName},#{attendee.email}) RETURNING *;

""")
    @ResultMap("attendeeMapper")

    Attendee addAttendee(@Param("attendee")AttendeeDTO attendee);

    //+++++++++++++++++++Update++++++++++++++++
     @Select("""
update attendees set attendee_name=#{attendee.attendeeName},email=#{attendee.email} where attendee_id=#{id} RETURNING *;
""")
     @ResultMap("attendeeMapper")
    Attendee updateAttendee(Integer id,@Param("attendee") AttendeeDTO attendee);


     //===================Dalete==============
    @Select("""
delete  from attendees where attendee_id=#{id} RETURNING *;
""")
    @ResultMap("attendeeMapper")
    Attendee deleteAttendee(Integer id);


    @Select("""
SELECT
    a.attendee_id,
    a.attendee_name,
    a.email,
    e.event_id,
    e.event_name,
    e.event_date
FROM event_attendee ea
         INNER JOIN events e ON ea.event_id = e.event_id
         INNER JOIN attendees a ON ea.attendee_id = a.attendee_id
WHERE e.event_id=#{id};
""")
    @ResultMap("attendeeMapper")
   Attendee getAttendeeByEventId(Integer id);




    @Select("""
  insert into event_attendee(event_id,attendee_id) values (#{event_id},#{attendee_id})
""")
    void insertEventAndAttendee(Integer event_id, Integer attendee_id);

}
