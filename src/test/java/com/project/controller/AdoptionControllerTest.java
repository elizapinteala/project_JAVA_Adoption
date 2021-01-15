package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.AdoptionEntity;
import com.project.entity.PersonEntity;
import com.project.exception.AdoptionException;
import com.project.exception.PersonException;
import com.project.model.AdoptionModel;
import com.project.model.AnimalSpecie;
import com.project.service.AdoptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdoptionController.class)
class AdoptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdoptionService adoptionService;

    private static AdoptionModel adoptionModel1;
    private static AdoptionModel adoptionModel2;

    @BeforeAll
    private static void setup(){
        LocalDate date = LocalDate.now();
        AdoptionModel adoptionModel1 = new AdoptionModel(date, 1, AnimalSpecie.DOG,1,1);
        AdoptionModel adoptionModel2 = new AdoptionModel(1,date, 1, AnimalSpecie.DOG,1,1);

    }

    /*@Test
    public void testCreateAdoption() throws Exception{
        AdoptionEntity adoptionEntity1 = adoptionService.modelToEntity(adoptionModel1);
        AdoptionEntity adoptionEntity2 = adoptionService.modelToEntity(adoptionModel2);
        when(adoptionService.saveAdoption(any())).thenReturn(adoptionEntity2);
        mockMvc.perform(post("/api/adoption")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(adoptionEntity2)))
                .andExpect(status().isCreated())
                .andReturn();


    }
*/
    /*@Test
    public void testGetAdoptionById() throws Exception{
        AdoptionEntity adoptionEntity = adoptionService.modelToEntity(adoptionModel2);
        Integer id = adoptionEntity.getIdAdoption();
        when(adoptionService.getAdoptionById(id)).thenReturn(Optional.of(adoptionEntity));
        mockMvc.perform(get("/api/adoption/byId/" + id))
                .andExpect(status().isOk())
                .andReturn();

        when(adoptionService.getAdoptionById(0)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/adoption/byId/" + 0))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof AdoptionException));


    }*/


   /* @Test
    public void testDeleteAdoption() throws Exception{
        AdoptionEntity adoptionEntity = adoptionService.modelToEntity(adoptionModel2);
        mockMvc.perform(delete("/api/adoption/delete/" + adoptionModel2.getIdAdoption()))
                .andExpect(status().isAccepted());

    }*/





}