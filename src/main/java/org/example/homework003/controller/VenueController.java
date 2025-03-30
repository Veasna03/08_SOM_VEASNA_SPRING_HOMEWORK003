package org.example.homework003.controller;

import jakarta.servlet.ServletRequest;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;
import org.example.homework003.model.response.ApiResponse;
import org.example.homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
public class VenueController {
    private  final VenueService venueService;



    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse<VenuesDTO>> createVenue(@RequestBody VenuesDTO venuesDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<VenuesDTO>builder()
                        .message("The venue has been successfully created")
                        .payload(venueService.addVenue(venuesDTO))
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
   @GetMapping("/{venue_id}")
    public ResponseEntity<ApiResponse<VenuesDTO>> getById(@PathVariable("venue_id") Integer venue_id) throws NotFoundException {
        ApiResponse<VenuesDTO> response=ApiResponse.<VenuesDTO>builder()
                .message("The venue has been successfully founded")
                .payload(venueService.getById(venue_id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venues>> updateVenueById(@PathVariable("venue-id") Integer id,@RequestBody VenuesDTO venuesDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .message("The venue has been successfully updated")
                        .payload(venueService.updateVenue(id, venuesDTO))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

   }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venues>> deleteVenueById(@PathVariable("venue-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .message("The venue has been successfully deleted")
                        .payload(venueService.deleteVenue(id))
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<VenuesDTO>>> getAllVenues() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<VenuesDTO>>builder()
                        .message("The venue has been successfully retrieved")
                        .payload(venueService.getAllVenues())
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
