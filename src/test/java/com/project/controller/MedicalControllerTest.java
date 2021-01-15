package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.MedicalEntity;
import com.project.model.MedicalModel;
import com.project.model.Medication;
import com.project.service.MedicalService;
import org.assertj.core.api.LocalDateAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalController.class)
class MedicalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MedicalService medicalService;

    private static MedicalModel medicalModel1;
    private static MedicalModel medicalModel2;

    @BeforeAll
    private static void setup() {
        LocalDate date = LocalDate.now();
        MedicalModel medicalModel1 = new MedicalModel(date, "Rabia", Medication.VACCIN, 10, 1);
        MedicalModel medicalModel2 = new MedicalModel(1, date, "Rabia", Medication.VACCIN, 10, 1);

    }

    /*@Test
    public void testCreateChart() throws Exception {
        MedicalEntity medicalEntity1 = medicalService.modelToEntity(medicalModel1);
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        when(medicalService.saveMedical(any())).thenReturn(medicalEntity2);
        mockMvc.perform(post("/api/medicaly")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(medicalEntity2)))
                .andExpect(status().isCreated())
                .andReturn();


    }*/

    /*@Test
    public void testGetChartById() throws Exception {
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        Integer id = medicalEntity2.getIdMedical();
        when(medicalService.getChartById(id)).thenReturn(Optional.of(medicalEntity2));
        mockMvc.perform(get("/api/adoption/byId/" + id))
                .andExpect(status().isOk())
                .andReturn();

        when(medicalService.getChartById(0)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/adoption/byId/" + 0))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }*/

    /*@Test
    public void testDeleteChart() throws Exception{
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        mockMvc.perform(delete("/api/adoption/delete/" + medicalModel2.getIdMedical()))
                .andExpect(status().isAccepted());
    }*/
}