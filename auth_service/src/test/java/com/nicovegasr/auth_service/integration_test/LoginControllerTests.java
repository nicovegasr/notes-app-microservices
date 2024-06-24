package com.nicovegasr.auth_service.integration_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = com.nicovegasr.auth_service.AuthServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class LoginControllerTests {
    /*
        1. Login user with credentials should return a JWT valid token with username and passwod information
        2. Login user with cypher oauth token should return a JWT valid token with username and passwod information cypher.
    */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginUserWithCredentialsShouldReturnToken() throws Exception {
        String requestBody = "{\"username\": \"test_user\", \"password\": \"test_password\"}";

        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}