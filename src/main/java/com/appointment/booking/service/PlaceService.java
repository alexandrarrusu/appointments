package com.appointment.booking.service;

import com.appointment.booking.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    void savePlace(Place place);
    Optional<Place> getPlaceById(Long id);
    List<Place> getAllPlaces();

}
