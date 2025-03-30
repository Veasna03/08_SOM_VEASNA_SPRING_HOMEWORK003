package org.example.homework003.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venues;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventName;
    private String eventDate;
    private Integer venueId;
    private Integer attendeeId;
}
