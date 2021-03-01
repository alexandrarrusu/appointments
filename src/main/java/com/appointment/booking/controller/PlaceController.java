package com.appointment.booking.controller;

import com.appointment.booking.entity.Place;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class PlaceController {

    private final PlaceServiceImpl placeService;

    @Autowired
    public PlaceController(PlaceServiceImpl placeService) {
        this.placeService = placeService;
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public ResponseEntity<Response<Place>> savePlace(@RequestBody Place place) {
        placeService.savePlace(place);
        return new ResponseEntity<>(new Response<>("Place added", "201", emptyList()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value="/place/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Place>> getPlaceById(@PathVariable Long id) {
        List<Place> list = new ArrayList<>();
        Optional<Place> p = placeService.getPlaceById(id);
        if(p.isPresent()) {
            list.add(p.get());
        } else {
            throw new NotFoundException("Place with id = " + id + " not found");
        }
        return new ResponseEntity<>(new Response<>("Place with id = " + id, "200", list),
                HttpStatus.OK);
    }

    @RequestMapping(value="/place", method = RequestMethod.GET)
    public ResponseEntity<Response<Place>> getAllPlaces() {
        return new ResponseEntity<>(new Response<>("Places found", "200",
                placeService.getAllPlaces()), HttpStatus.OK);
    }
}
