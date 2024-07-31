package com.upin.Project.Social.App.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upin.Project.Social.App.dto.request.RegisterRequest;
import com.upin.Project.Social.App.dto.response.RegisterResponse;
import com.upin.Project.Social.App.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private RegisterRequest request;
    private RegisterResponse registerResponse;

    @BeforeEach
    void initData(){
        request = RegisterRequest.builder()
                .username("upinmc123")
                .password("12345678")
                .rePassword("12345678")
                .build();
        registerResponse = RegisterResponse.builder()
                .id("adafawadsx")
                .username("upinmc123")
                .roleName("USER")
                .build();
    }

    @Test
    // tại sao lại viết hàm test này
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        when(userService.createUser(any())).thenReturn(registerResponse);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/users/add")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1000)
        );

    }

    @Test
    //
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        request.setUsername("up");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);


        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value(3002))
                .andExpect(jsonPath("message").value("Invalid username, must be least 3 characters"))
        ;

    }
}
