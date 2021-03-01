package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Place;
import com.appointment.booking.repository.PlaceRepository;
import com.appointment.booking.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    @Override
    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
