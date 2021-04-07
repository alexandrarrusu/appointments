package com.appointment.booking.controller;

import com.appointment.booking.DatabaseConfig;
import com.appointment.booking.email.EmailService;
import com.appointment.booking.entity.Appointment;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.AppointmentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(DatabaseConfig.class)
@SpringBootTest
public class AppointmentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private EmailService emailService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Before
    public void setup() {
        AppointmentController appointmentController = new AppointmentController(appointmentService, emailService);
        AppointmentController appointmentControllerSpy = spy(appointmentController);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentControllerSpy).build();
    }

    @Test
    public void saveAppointment() throws Exception {
        ResultActions result = mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"date\" : \"10/04/2021\", \"time\" : \"10:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\", \"creationTime\" : \"1617723942000\", \"updateTime\" : \"1617723942000\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        String appointmentString = "{\"id\" : \"1\", \"date\" : \"10/04/2021\", \"time\" : \"10:00\", " +
                "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                "\"company_id\" : \"1\", \"creationTime\" : \"1617723942000\", \"updateTime\" : \"1617723942000\" }";
        Appointment appointment = objectMapper.readValue(appointmentString, Appointment.class);
        verify(emailService, times(1)).sendEmailToClient(appointment);

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("201", o.getCode());
        assertEquals("Appointment added", o.getMessage());
        assertEquals(1, o.getResults().size());
    }

    @Test
    public void getAppointmentById() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"date\" : \"10/04/2021\", \"time\" : \"10:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\", \"creationTime\" : \"1617723942000\", " +
                        "\"updateTime\" : \"1617723942000\" }"));

        ResultActions result = mockMvc.perform(get("/appointment/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("200", o.getCode());
        assertEquals("Appointment with id = 1", o.getMessage());
        assertEquals(1, o.getResults().size());
    }

    @Test
    public void getAllAppointments() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"date\" : \"10/04/2021\", \"time\" : \"10:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\", \"creationTime\" : \"1617723942000\", " +
                        "\"updateTime\" : \"1617723942000\" }"));

        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"2\", \"date\" : \"16/04/2021\", \"time\" : \"10:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\", \"creationTime\" : \"1617723942000\", " +
                        "\"updateTime\" : \"1617723942000\" }"));

        ResultActions result = mockMvc.perform(get("/appointment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("200", o.getCode());
        assertEquals("Appointments found", o.getMessage());
        assertEquals(2, o.getResults().size());
    }

    @Test
    public void updateAppointment() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"date\" : \"10/04/2021\", \"time\" : \"10:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\"}"));

        ResultActions result = mockMvc.perform(patch("/appointment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"date\" : \"21/04/2021\", \"time\" : \"15:00\", " +
                        "\"client_id\" : \"1\", \"employee_id\" : \"1\", \"offer_id\" : \"1\"," +
                        "\"company_id\" : \"1\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("201", o.getCode());
        assertEquals("Appointment updated", o.getMessage());
    }

}