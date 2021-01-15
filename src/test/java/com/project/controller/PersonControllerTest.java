package com.project.controller;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.AdminEntity;
import com.project.entity.PersonEntity;
import com.project.exception.PersonException;
import com.project.model.PersonModel;
import com.project.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    public void testCreatePerson() throws Exception{
        PersonEntity personEntity = new PersonEntity("Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        when(personService.savePerson(any())).thenReturn(new PersonEntity(1,"Eliza","Pinteala","0756932885","elizapinteala@gmail.com"));
        mockMvc.perform(post("/api/person")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(personEntity)))
                .andExpect(status().isCreated())
                .andReturn();


    }

    @Test
    public void testGetPersonById() throws Exception{
        PersonEntity personEntity = new PersonEntity(3,"Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        Integer id = personEntity.getIdPerson();
        when(personService.getPersonById(id)).thenReturn(Optional.of(personEntity));
        mockMvc.perform(get("/api/person/byId/" + id))
                .andExpect(status().isOk())
                .andReturn();

        when(personService.getPersonById(0)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/person/byId/" + 0))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result1 -> assertTrue(result1.getResolvedException() instanceof PersonException));


    }

    @Test
    public void testGetPersonByEmail() throws Exception{
        PersonEntity personEntity = new PersonEntity(3,"Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        String email = personEntity.getEmail();
        when(personService.getPersonByEmail(email)).thenReturn(Optional.of(personEntity));
        mockMvc.perform(get("/api/person/byEmail/" + email))
                .andExpect(status().isOk())
                .andReturn();

        when(personService.getPersonByEmail("0")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/person/byEmail/" + "0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect( result1-> assertTrue(result1.getResolvedException() instanceof PersonException));

    }


    /*@Test
    public void testRemovePersonByEmail() throws Exception{
        PersonEntity personEntity = new PersonEntity(3,"Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        String email = personEntity.getEmail();

        when(personService.getPersonByEmail(email)).thenReturn(Optional.of(personEntity));
        when(personService.removePersonByEmail(email)).thenReturn(true);
        mockMvc.perform(delete("/api/person/email"))
                .andExpect(status().isOk());

        when(personService.getPersonByEmail("0")).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/person/email"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect( result1-> assertTrue(result1.getResolvedException() instanceof PersonException));


    }*/

    @Test
    public void testUpdatePerson() throws Exception{
        PersonEntity personEntity = new PersonEntity(3,"Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        Integer id = personEntity.getIdPerson();
        when(personService.updatePerson(id, personService.entityToModel(personEntity))).thenReturn(personEntity);
        mockMvc.perform(put("/api/person/" +id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(personEntity)))
                .andExpect(status().isOk())
                .andReturn();
    }
}