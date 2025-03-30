package org.example.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.exception.ErrorAttendee;
import org.example.homework003.exception.NotFoundExceptions;
import org.example.homework003.exception.NullPointerExceptino;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.request.AttendeeDTO;
import org.example.homework003.repository.AttendeeRepository;
import org.example.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Attendee> getAllAttendees(Integer size,Integer page) {
        Map<String,String> error = new HashMap<>();
        if (size <= 0) {
            error.put("offset","must be greater than 0");
        }
        else if (page <= 0) {
            error.put("page","must be greater than 0");
        }
        if(!error.isEmpty()) {
            throw new NullPointerExceptino(error);
        }



        return attendeeRepository.getAllAttendees(size,page);
    }

    @Override
    public Attendee getAttendeeByID(Integer id) throws NotFoundException {
       Attendee attendee= attendeeRepository.getAttendeeByEventId(id);
       if(attendee==null){
           throw new NotFoundExceptions(" attendee id : "+id+" has not been found");
       }
        return attendeeRepository.getAttendeeById(id);
    }

//    @Override
//    public Attendee addAttendee(AttendeeDTO attendeeDTO) {
//        if (attendeeDTO.getEmail()=="" && attendeeDTO.getAttendeeName()=="") {
//          throw new NullPointerExceptino(Map.of("AttendeeName","must not be null"));
//        }
//        else if(!attendeeDTO.getEmail().contains("@gmail.com")) {
//            throw new NullPointerExceptino("must ne a well form email address");
//        }
//         else if (attendeeDTO.getAttendeeName() == null || attendeeDTO.getAttendeeName().isEmpty()) {
//            throw new NullPointerExceptino("Attendee name cannot be null or empty");
//        } else if (attendeeDTO.getEmail() == null || attendeeDTO.getEmail().isEmpty()) {
//            throw new NullPointerExceptino("Email cannot be null or empty");
//        }
//        return attendeeRepository.addAttendee(attendeeDTO);
//    }
@Override
public Attendee addAttendee(AttendeeDTO attendeeDTO) {
    Map<String, String> errors = new HashMap<>();
    if(attendeeDTO.getAttendeeName()=="" && attendeeDTO.getEmail()==""){
        errors.put("attendeeName","must not be blank");
        errors.put("email","must not be blank");
    }
     else if (attendeeDTO.getAttendeeName() == null || attendeeDTO.getAttendeeName().isEmpty()) {
        errors.put("AttendeeName", "must not be blank");  // Matches the JSON format in the image
    }
    else if ( !attendeeDTO.getEmail().endsWith("@gmail.com")) {
        errors.put("email", "must be a well-formed email address ending with @gmail.com");
    }
     else if (attendeeDTO.getEmail() == null || attendeeDTO.getEmail().isEmpty()) {
        errors.put("email", "must not be blank");
    }
     if (!errors.isEmpty()) {
        throw new NullPointerExceptino(errors);
    }

    return attendeeRepository.addAttendee(attendeeDTO);
}


    @Override
    public Attendee updateAttendee(Integer id, AttendeeDTO attendeeDTO) {
        Map<String, String> errors = new HashMap<>();
            Attendee attendee= attendeeRepository.getAttendeeById(id);
            if(attendee==null){
                throw new NotFoundExceptions(" Could not update ,id : "+id+" is not found");
            }
        if (attendeeDTO.getEmail() == "" && attendeeDTO.getAttendeeName() == "") {

            errors.put("attendeeName", "must not be blank");
            errors.put("email","must not be blank");
        }
            else if(attendeeDTO.getAttendeeName()==""){
                errors.put("attendeeName","must not be blank");
            }
            else if(!attendeeDTO.getEmail().endsWith("@gmail.com")&&  !attendeeDTO.getAttendeeName().isEmpty() &&!attendeeDTO.getEmail().isEmpty()) {
            errors.put("email", "must be a well-formed email address ending with @gmail.com");
        }
            else if(attendeeDTO.getEmail()==""){
                errors.put("email","must not be blank");
            }
          if(!errors.isEmpty()) {
              throw new NullPointerExceptino(errors);
          }

        return attendeeRepository.updateAttendee(id, attendeeDTO);
    }
    @Override
    public Attendee deleteAttendee(Integer id) {
           if(attendeeRepository.getAttendeeById(id)==null){
               throw new NotFoundExceptions(" Attendee id : "+id+" is not found");
           }
        return attendeeRepository.deleteAttendee(id);
    }

    @Override
    public Attendee getAttendeeByEventId(Integer eventId) {
        return attendeeRepository.getAttendeeByEventId(eventId);
    }
}
