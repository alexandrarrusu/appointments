package com.appointment.booking.repository;

import com.appointment.booking.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p.name from Place p where p.id = :id")
    String getPlaceNameById(Long id);
}
