package org.example.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework003.model.entity.Venues;
import org.example.homework003.model.request.VenuesDTO;

import java.util.List;

@Mapper
public interface VenuesRepository {

    @Select("""
    insert  into venues(venue_name,location) values (#{v.venueName},#{v.location})
    RETURNING *;
""")
    @Results(id = "venueMapper",value = {
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    Venues create(@Param("v") VenuesDTO venues);

    @Select("""
select * from venues;
""")
    @ResultMap("venueMapper")
    List<Venues> getAllvenue();

    @Select("""
select * from venues where venue_id = #{venueId};
""")
    @ResultMap("venueMapper")
    Venues getvenueById(Integer venueId);


    @Select("""
update venues set venue_name=#{v.venueName},location=#{v.location} where venue_id=#{id}
RETURNING * ;
""")
    @ResultMap("venueMapper")
    Venues updatevenue(Integer id, @Param("v") VenuesDTO venues);

    @Select("""
delete from venues where venue_id = #{id}
RETURNING * ;

""")
    @ResultMap("venueMapper")
    Venues deletevenue(Integer id);








}
