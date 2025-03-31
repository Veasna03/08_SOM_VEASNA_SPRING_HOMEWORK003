package org.example.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private Venues venue;
    private List<Attendee> attendee;
}
