package com.appointment.booking.repository;

import com.appointment.booking.entity.Client;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void getClientEmailById() {
        Client client = new Client.ClientBuilder().firstname("Anna").lastname("Stone").email("anna.stone@something.com")
                .phone("0789556622").build();
        testEntityManager.persist(client);
        testEntityManager.flush();
        assertEquals(client.getEmail(), clientRepository.getClientEmailById(client.getId()));
    }

}