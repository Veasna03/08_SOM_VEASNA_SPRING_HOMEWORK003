package org.example.homework003.exception;

import jdk.jfr.Enabled;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.request.AttendeeDTO;

import java.util.List;
import java.util.Map;

public class NullPointerExceptino  extends RuntimeException{
    public NullPointerExceptino(String message) {
        super(message);
    }
    public NullPointerExceptino(Map<String,String> entity) {
        super(entity.toString());
    }

}
