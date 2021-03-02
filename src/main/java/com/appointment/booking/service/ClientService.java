package com.appointment.booking.service;

import com.appointment.booking.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void saveClient(Client client);
    Optional<Client> getClientById(Long id);
    List<Client> getAllClients();
}
