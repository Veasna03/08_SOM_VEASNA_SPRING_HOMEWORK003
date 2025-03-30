package org.example.homework003.service.impl;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.exception.NotFoundExceptions;
import org.example.homework003.exception.NullPointerExceptino;
import org.example.homework003.mapper.VenuesMapper;
import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;
import org.example.homework003.repository.VenuesRepository;
import org.example.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VenueServiceImpl implements VenueService {

    private  VenuesRepository venuesRepository;
    private  VenuesMapper venuesMapper;
    public VenueServiceImpl(VenuesRepository venuesRepository, VenuesMapper venuesMapper) {
        this.venuesRepository = venuesRepository;
        this.venuesMapper = venuesMapper;
    }

    @Override
    public Venues addVenue(VenuesDTO venue) {
              Map<String,String> error = new HashMap<>();
              if(venue.getVenueName().isEmpty() && venue.getLocation().isEmpty()){
                  error.put("VenueName","must not be blank");
                  error.put("Location","must not be blank");
              }
              else if(venue.getVenueName().isEmpty()){
                  error.put("VenueName","must not be blank");
              }
              else if(venue.getLocation().isEmpty()){
                  error.put("Location","must not be blank");
              }
              if(!error.isEmpty()){
                  throw new NullPointerExceptino(error);
              }
             return venuesRepository.create(venue);
    }


    @Override
    public Venues getById(Integer id) throws NotFoundException {
          Venues venues= venuesRepository.getvenueById(id);
          if(venues==null){
              throw new NotFoundExceptions("Venue id: "+id+"  not found");
          }
        return venuesRepository.getvenueById(id);
    }

    @Override
    public Venues updateVenue(Integer id, VenuesDTO venue) {
        if(venuesRepository.getvenueById(id)==null){
            throw new NotFoundExceptions("Venue id: "+id+"  not found");
        }
        Map<String,String> error=new HashMap<>();
        if(venue.getVenueName().isEmpty() && venue.getLocation().isEmpty()){
            error.put("venueRequest","must not be blank");
        }
        else if(venue.getVenueName().isEmpty()){
            error.put("venueRequest","must not be blank");
        }
        else if(venue.getLocation().isEmpty()){
            error.put("venueRequest","must not be blank");
        }
        if (!error.isEmpty()){
            throw new NullPointerExceptino(error);
        }
        return venuesRepository.updatevenue(id, venue);
    }

    @Override
    public Venues deleteVenue(Integer id) {
        if(venuesRepository.getvenueById(id)==null){
            throw new NotFoundExceptions("Venue id: "+id+"  not found");
        }
        return venuesRepository.deletevenue(id);
    }

    @Override
    public List<Venues> getAllVenues(Integer size,Integer page) {
        Map<String,String > error= new HashMap<>();
        if (size <= 0) {
            error.put("offset","must be greater than 0");
        }
        else if (page <= 0) {
            error.put("page","must be greater than 0");
        }
        if(!error.isEmpty()) {
            throw new NullPointerExceptino(error);
        }




        return venuesRepository.getAllvenue(size,page);
    }
}


