package com.appointment.booking.controller;

import com.appointment.booking.entity.Client;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<Response<Client>> saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return new ResponseEntity<>(new Response<>("Client added", "201", emptyList()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value="/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Client>> getClientById(@PathVariable Long id) {
        List<Client> list = new ArrayList<>();
        Optional<Client> c = clientService.getClientById(id);
        if(c.isPresent()) {
            list.add(c.get());
        } else {
            throw new NotFoundException("Client with id = " + id + " not found");
        }

        return new ResponseEntity<>(new Response<>("Client with id = " + id, "200", list),
                HttpStatus.OK);
    }

    @RequestMapping(value="/client", method = RequestMethod.GET)
    public ResponseEntity<Response<Client>> getAllClients() {
        return new ResponseEntity<>(new Response<>("Clients found", "200",
                clientService.getAllClients()), HttpStatus.OK);
    }
}
