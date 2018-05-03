package com.example.integration;

import com.example.config.AbstractApplicationTest;
import com.example.domain.model.json.request.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthenticationTest extends AbstractApplicationTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void loadContext() {
        super.setUpContext();
    }

    @Test
    public void testLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "123");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        String token = super.mockMvcPerformResult("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE);
//        AuthenticationResponse authenticationResponse = mapper.readValue(token, AuthenticationResponse.class);
        Assert.assertNotNull(token);
    }

    @Test
    public void testBadCredentials() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "6666");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        int status = super.mockMvcLoginPost("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isUnauthorized());
        Assert.assertEquals(401, status);
    }

}
