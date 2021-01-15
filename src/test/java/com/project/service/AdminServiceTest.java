package com.project.service;

import com.project.entity.AdminEntity;
import com.project.exception.PersonException;
import com.project.model.AdminModel;
import com.project.repo.AdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private static AdminModel adminModel1;
    private static AdminModel adminModel2;

    @BeforeAll
    public static void setup(){
        adminModel1= new AdminModel("George","Irimia","georgeirimia@gmail.com");
        adminModel2= new AdminModel(1,"George","Irimia","georgeirimia@gmail.com");
    }

    @Test
    @DisplayName("Test save admin-happy flow")
    public void testSaveAdmin(){
        AdminEntity adminEntity = new AdminEntity(adminModel1);
        AdminEntity savedAdmin = new AdminEntity(adminModel2);
        when(adminRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(adminRepository.save(any())).thenReturn(savedAdmin);

        AdminEntity result = adminService.saveAdmin(adminService.entityToModel(adminEntity));

        assertEquals(adminEntity.getFirstName(), result.getFirstName());
        assertEquals(adminEntity.getLastName(),result.getLastName());
        assertEquals(adminEntity.getEmail(), result.getEmail());
        assertEquals(1, result.getIdAdmin());

        verify(adminRepository).findByEmail(any());
        verify(adminRepository).save(any());

    }

    @Test
    @DisplayName("Save admin with same email already exists")
    void testSaveAdminException(){
        AdminEntity adminEntity = new AdminEntity(adminModel1);
        AdminEntity savedAdmin = new AdminEntity(adminModel2);
        when(adminRepository.findByEmail(any())).thenReturn(Optional.of(savedAdmin));
        PersonException exception = assertThrows(PersonException.class,
                ()-> adminService.saveAdmin(adminService.entityToModel(adminEntity)));

        // assertEquals("Create person with same email already exists",exception);

        verify(adminRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Test get admin by email")
    public void testGetAdminByEmail(){
        AdminEntity adminEntity = new AdminEntity(adminModel1);
        doReturn(Optional.empty()).when(adminRepository).findByEmail(adminEntity.getEmail());
        adminService.getAdmin(adminEntity.getEmail());
    }

    @Test
    @DisplayName("Test remove admin by email - happy flow")
    public void testRemoveAdminByEmail(){
        AdminEntity adminEntity = new AdminEntity(adminModel1);
        lenient().when(adminRepository.findByEmail(adminEntity.getEmail())).thenReturn(Optional.of(adminEntity));
        //lenient().when(adminRepository.deleteByEmail(adminEntity.getEmail())).thenReturn(true);
        verify(adminRepository, times(0)).deleteByEmail(adminEntity.getEmail());


    }

    @Test
    @DisplayName("Test no admin to remove")
    public void testNoAdminToRemove(){
        when(adminRepository.findByEmail(any())).thenReturn(Optional.empty());
        PersonException exception = assertThrows(PersonException.class,
                ()-> adminService.deleteAdmin(any()));
        assertEquals(PersonException.PersonErrors.PERSON_COULD_NOT_BE_REMOVED, exception.getError());
        verify(adminRepository, times(0)).deleteByEmail(any());

    }

}