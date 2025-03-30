package org.example.homework003.service.impl;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.exception.NotFoundExceptions;
import org.example.homework003.mapper.VenuesMapper;
import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;
import org.example.homework003.repository.VenuesRepository;
import org.example.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private  VenuesRepository venuesRepository;
    private  VenuesMapper venuesMapper;
    public VenueServiceImpl(VenuesRepository venuesRepository, VenuesMapper venuesMapper) {
        this.venuesRepository = venuesRepository;
        this.venuesMapper = venuesMapper;
    }

    @Override
    public VenuesDTO addVenue(VenuesDTO venue) {
             Venues venues=  venuesRepository.create(venue);
        return  venuesMapper.toVenuesDTO(venues);
    }


    @Override
    public VenuesDTO getById(Integer id) throws NotFoundException {
          Venues venues= venuesRepository.getvenueById(id);
          if(venues==null){
              throw new NotFoundExceptions("Venue id: "+id+"  not found");
          }
        return venuesMapper.toVenuesDTO(venuesRepository.getvenueById(id));
    }

    @Override
    public Venues updateVenue(Integer id, VenuesDTO venue) {
        return venuesRepository.updatevenue(id, venue);
    }

    @Override
    public Venues deleteVenue(Integer id) {
        return venuesRepository.deletevenue(id);
    }

    @Override
    public List<VenuesDTO> getAllVenues() {


        return venuesMapper.toVenuesDTO(venuesRepository.getAllvenue());
    }
}


