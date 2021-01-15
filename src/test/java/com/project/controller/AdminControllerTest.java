package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.AdminEntity;
import com.project.exception.PersonException;
import com.project.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminService adminService;

    @Test
    public void testCreateAdmin() throws Exception{
        AdminEntity adminEntity = new AdminEntity("George","Irimia","georgeirimia@gmail.com");
        when(adminService.saveAdmin(any())).thenReturn(new AdminEntity(1,"George","Irimia","georgeirimia@gmail.com"));
        mockMvc.perform(post("/api/admin")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(adminEntity)))
                .andExpect(status().isCreated())
                .andReturn();


    }

    @Test
    public void testGetAdminByEmail() throws Exception{
        AdminEntity adminEntity = new AdminEntity(3,"George","Irimia","georgeirimia@gmail.com");
        String email = adminEntity.getEmail();
        when(adminService.getAdmin(email)).thenReturn(Optional.of(adminEntity));
        mockMvc.perform(get("/api/admin/byEmail/" + email))
                .andExpect(status().isOk())
                .andReturn();

        when(adminService.getAdmin("0")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/admin/byEmail/" + "0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PersonException));

    }









}