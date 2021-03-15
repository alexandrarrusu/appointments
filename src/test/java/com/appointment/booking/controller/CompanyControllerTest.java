package com.appointment.booking.controller;

import com.appointment.booking.DatabaseConfig;
import com.appointment.booking.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(DatabaseConfig.class)
@SpringBootTest
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveCompany() throws Exception {
        ResultActions result = mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"name\" : \"Place1\", \"address\" : \"Stauffacher\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("201", o.getCode());
        assertEquals("Company added", o.getMessage());
        assertEquals(1, o.getResults().size());
    }

    @Test
    public void getCompanyById() throws Exception {
        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"name\" : \"Place1\", \"address\" : \"Stauffacher\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        ResultActions result = mockMvc.perform(get("/company/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("200", o.getCode());
        assertEquals("Company with id = 1", o.getMessage());
        assertEquals(1, o.getResults().size());
    }

    @Test
    public void getAllCompanies() throws Exception {
        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\", \"name\" : \"Place1\", \"address\" : \"Stauffacher\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"2\", \"name\" : \"Place2\", \"address\" : \"Central\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        ResultActions result = mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult response = result.andReturn();
        String contentAsString = response.getResponse().getContentAsString();
        Response<?> o = objectMapper.readValue(contentAsString, Response.class);
        assertEquals("200", o.getCode());
        assertEquals("Companies found", o.getMessage());
        assertEquals(2, o.getResults().size());
    }
}
