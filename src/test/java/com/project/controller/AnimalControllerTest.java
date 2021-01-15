package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.AnimalEntity;
import com.project.entity.PersonEntity;
import com.project.exception.AnimalException;
import com.project.exception.PersonException;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.model.Gender;
import com.project.service.AnimalService;
import org.junit.jupiter.api.DisplayName;
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

@WebMvcTest(controllers = AnimalController.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnimalService animalService;

    LocalDate checkInDate = LocalDate.now();
    LocalDate adoptionDate = LocalDate.now();

    @Test
    public void testCreateAnimal() throws Exception{
        AnimalEntity animalEntity = new AnimalEntity("Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate);
        when(animalService.saveAnimal(any())).thenReturn(new AnimalEntity(1,"Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate));
        mockMvc.perform(post("/api/animal")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(animalEntity)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testGetAnimalById() throws Exception{
        AnimalEntity animalEntity = new AnimalEntity(1,"Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate);
        Integer id = animalEntity.getIdAnimal();
        when(animalService.getAnimalById(id)).thenReturn(Optional.of(animalEntity));
        mockMvc.perform(get("/api/animal/byId/" + id))
                .andExpect(status().isOk())
                .andReturn();

        when(animalService.getAnimalById(0)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/animal/byId/" + 0))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof AnimalException));

    }


    @Test
    public void testUpdateAnimal() throws Exception{
        AnimalEntity animalEntity = new AnimalEntity(1,"Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate);
        Integer id = animalEntity.getIdAnimal();
        when(animalService.updateAnimal(id, animalService.entityToModel(animalEntity))).thenReturn(animalEntity);
        mockMvc.perform(put("/api/animal/" +id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(animalEntity)))
                .andExpect(status().isOk())
                .andReturn();

        /*when(animalService.updateAnimal(0, animalService.entityToModel(animalEntity))).thenReturn(animalEntity);
        mockMvc.perform(put("/api/animal/" +0)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(animalEntity)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof PersonException));*/
    }


}