package org.example.homework003.model.request;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.homework003.model.entity.Attendee;
import org.example.homework003.model.entity.Venues;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventName;
    private String eventDate;
    private Integer venueId;
    private List<Integer> attendeeId;
}
