package com.project.service;

import com.project.entity.PersonEntity;
import com.project.entity.VetEntity;
import com.project.exception.PersonException;
import com.project.model.VetModel;
import com.project.repo.VetRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetServiceTest {
    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetService vetService;

    private static VetModel vetModel1;
    private static VetModel vetModel2;
    private static VetModel vetModel3;

    @BeforeAll
    public static void setup(){
        vetModel1 = new VetModel("123456","Jon","Doe","jondoe@gmail.com");
        vetModel2 = new VetModel(1,"123456","Jon","Doe","jondoe@gmail.com");
        vetModel3 = new VetModel(1,"","Jon","Doe","jondoe@gmail.com");
    }

    @Test
    @DisplayName("Save vet with same license already exists")
    void testSaveVetException(){
        VetEntity vetEntity = new VetEntity(vetModel1);
        VetEntity savedVet = new VetEntity(vetModel2);
        when(vetRepository.findByLicense(any())).thenReturn(Optional.of(savedVet));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> vetService.saveVet(vetService.entityToModel(vetEntity)));


        verify(vetRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Test save vet-happy flow")
    public void testSaveVet(){
        VetEntity vetEntity = new VetEntity(vetModel1);
        VetEntity savedVet = new VetEntity(vetModel2);
        when(vetRepository.findByLicense(any())).thenReturn(Optional.empty());
        when(vetRepository.save(any())).thenReturn(savedVet);

        VetEntity result = vetService.saveVet(vetService.entityToModel(vetEntity));

        assertEquals(vetEntity.getFirstName(), result.getFirstName());
        assertEquals(vetEntity.getLastName(),result.getLastName());
        assertEquals(vetEntity.getEmail(), result.getEmail());
        assertEquals("123456", result.getLicense());

        verify(vetRepository).findByLicense(any());
        verify(vetRepository).save(any());

    }

    @Test
    @DisplayName("Test get vet by Id")
    public void testGetVetById(){
        VetEntity vetEntity = new VetEntity(vetModel1);
        doReturn(Optional.empty()).when(vetRepository).findByIdVet(vetEntity.getIdVet());
        vetService.getVetById(vetEntity.getIdVet());
    }

    @Test
    @DisplayName("Test get vet by license")
    public void testGetVetByLicense(){
        VetEntity vetEntity1 = new VetEntity(vetModel1);
        VetEntity vetEntity2 = new VetEntity(vetModel3);
        doReturn(Optional.empty()).when(vetRepository).findByLicense(vetEntity1.getLicense());
        vetService.getVetByLicense(vetEntity1.getLicense());


    }

    @Test
    @DisplayName("Test remove vet by license - happy flow")
    public void testRemoveVetByLicense(){
        VetEntity vetEntity = new VetEntity(vetModel1);
        lenient().when(vetRepository.findByLicense(vetEntity.getLicense())).thenReturn(Optional.of(vetEntity));
        vetRepository.deleteByLicense(vetEntity.getLicense());
        verify(vetRepository, times(1)).deleteByLicense(vetEntity.getLicense());
    }

    @Test
    @DisplayName("Test no vet to remove")
    public void testNoVetToRemove(){
        when(vetRepository.findByLicense(any())).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> vetService.removeVet(any()));
        //assertEquals("No person to delete",exception);
        verify(vetRepository, times(0)).deleteByLicense(any());

    }

}

