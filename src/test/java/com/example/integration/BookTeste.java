package com.example.integration;

import com.example.config.AbstractApplicationTest;
import com.example.model.json.request.AuthenticationRequest;
import com.example.model.json.response.AuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BookTeste extends AbstractApplicationTest {

    String token;


    @Before
    public void setup(){
        super.setUpContext();
    }

    @Before
    public void login() throws  Exception{
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin","123");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        token = super.mockMvcPerformResult("/api/auth",jsonInString,MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = mapper.readValue(token, AuthenticationResponse.class);
        Assert.assertNotNull(authenticationResponse);
    }

    @Test
    public void createBookTest(){
        int status = super.mockMvcPerformAuthenticatedPostStatus("/api/book")
    }


}
