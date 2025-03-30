package org.example.homework003.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;

import java.util.List;

public interface VenueService {
    Venues addVenue(VenuesDTO venue);
    Venues getById(Integer id) throws NotFoundException;
    Venues updateVenue(Integer id,VenuesDTO venue);
    Venues deleteVenue(Integer id);
    List<Venues> getAllVenues(Integer size,Integer page);




}
