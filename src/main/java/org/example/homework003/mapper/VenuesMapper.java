package org.example.homework003.mapper;

import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenuesMapper {

    VenuesDTO toVenuesDTO(Venues venues);
    List<VenuesDTO>  toVenuesDTO(List<Venues> venues);

}
