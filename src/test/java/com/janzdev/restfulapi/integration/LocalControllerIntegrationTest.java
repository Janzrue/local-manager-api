package com.janzdev.restfulapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.repository.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LocalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Local local;

    @BeforeEach
    void setUp() {
        localRepository.deleteAll();
        local = Local.builder()
                .name("Test Local")
                .floor("First Floor")
                .code("TEST001")
                .build();
        local = localRepository.save(local);
    }

    @Test
    @WithMockUser(roles = "READER")
    void shouldGetAllLocals() throws Exception {
        mockMvc.perform(get("/api/v1/locals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Local"));
    }

    @Test
    @WithMockUser(roles = "READER")
    void shouldGetLocalById() throws Exception {
        mockMvc.perform(get("/api/v1/locals/id/{id}", local.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Local"))
                .andExpect(jsonPath("$.code").value("TEST001"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    void shouldCreateLocal() throws Exception {
        Local newLocal = Local.builder()
                .name("New Local")
                .floor("Second Floor")
                .code("NEW001")
                .build();

        mockMvc.perform(post("/api/v1/locals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newLocal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Local"))
                .andExpect(jsonPath("$.code").value("NEW001"));
    }

    @Test
    @WithMockUser(roles = "READER")
    void shouldReturnForbiddenWhenCreatingLocalWithoutPermission() throws Exception {
        Local newLocal = Local.builder()
                .name("New Local")
                .floor("Second Floor")
                .code("NEW001")
                .build();

        mockMvc.perform(post("/api/v1/locals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newLocal)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteLocal() throws Exception {
        mockMvc.perform(delete("/api/v1/locals/{id}", local.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully deleted"));
    }
}