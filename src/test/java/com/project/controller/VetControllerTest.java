package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.PersonEntity;
import com.project.entity.VetEntity;
import com.project.exception.PersonException;
import com.project.service.VetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VetController.class)
class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VetService vetService;

    @Test
    public void testCreateVet() throws Exception{
        VetEntity vetEntity = new VetEntity("123456","Jon","Doe","jondoe@gmail.com");
        when(vetService.saveVet(any())).thenReturn(new VetEntity(1,"123456","Jon","Doe","jondoe@gmail.com"));
        mockMvc.perform(post("/api/vet")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vetEntity)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testGetVetById() throws Exception{
        VetEntity vetEntity = new VetEntity(1,"123456","Jon","Doe","jondoe@gmail.com");
        Integer id = vetEntity.getIdVet();
        when(vetService.getVetById(id)).thenReturn(Optional.of(vetEntity));
        mockMvc.perform(get("/api/vet/byId/" + id))
                .andExpect(status().isOk())
                .andReturn();

        when(vetService.getVetById(0)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/vet/byId/" + 0))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void testGetVetByLicense() throws Exception{
        VetEntity vetEntity = new VetEntity(1,"123456","Jon","Doe","jondoe@gmail.com");
        String license = vetEntity.getLicense();
        when(vetService.getVetByLicense(license)).thenReturn(Optional.of(vetEntity));
        MvcResult result = mockMvc.perform(get("/api/vet/byLicense/" + license))
                .andExpect(status().isOk())
                .andReturn();

        when(vetService.getVetByLicense("licenseXXX")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/person/byLicense/" +"licenseXXX"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }




}