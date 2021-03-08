package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Client;
import com.appointment.booking.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @Before
    public void setup() {
        client = new Client.ClientBuilder().id(1L).firstname("Joe").lastname("Santana")
                .email("joe.santana@something.com").phone("0785342678")
                .build();
    }

    @Test
    public void saveClient() {
        when(clientRepository.save(client)).thenReturn(client);
        Client c = clientService.saveClient(client);
        assertEquals(client.getId(), c.getId());
        assertEquals(client.getFirstname(), c.getFirstname());
        assertEquals(client.getLastname(), c.getLastname());
        assertEquals(client.getEmail(), c.getEmail());
        assertEquals(client.getPhone(), c.getPhone());
    }

    @Test
    public void getClientById() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        Optional<Client> c = clientService.getClientById(client.getId());
        Client customer = c.get();
        assertEquals(client.getFirstname(), customer.getFirstname());
        assertEquals(client.getLastname(), customer.getLastname());
        assertEquals(client.getEmail(), customer.getEmail());
        assertEquals(client.getPhone(), customer.getPhone());
    }

    @Test
    public void getAllClients() {
        Client secondClient = new Client.ClientBuilder().id(1L).firstname("John").lastname("Buffalo")
                .email("john.buffalo@something.com").phone("0768234645")
                .build();
        List<Client> list = new ArrayList<>();
        list.add(secondClient);
        list.add(client);
        when(clientRepository.findAll()).thenReturn(list);
        List<Client> clients = clientService.getAllClients();
        assertEquals(list.size(), clients.size());
    }

    @Test
    public void getClientEmailById() {
        when(clientRepository.getClientEmailById(client.getId())).thenReturn(client.getEmail());
        assertEquals(client.getEmail(), clientService.getClientEmailById(client.getId()));
    }
}
