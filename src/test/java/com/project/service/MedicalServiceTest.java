package com.project.service;

import com.project.entity.AdminEntity;
import com.project.entity.MedicalEntity;
import com.project.exception.PersonException;
import com.project.model.MedicalModel;
import com.project.model.Medication;
import com.project.repo.MedicalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MedicalServiceTest {

    @Mock
    private MedicalRepository medicalRepository;

    @InjectMocks
    private MedicalService medicalService;

    private static MedicalModel medicalModel1;
    private static MedicalModel medicalModel2;

    @BeforeAll
    private static void setup() {
        LocalDate date = LocalDate.now();
        MedicalModel medicalModel1 = new MedicalModel(date, "Rabia", Medication.VACCIN, 10, 1);
        MedicalModel medicalModel2 = new MedicalModel(1, date, "Rabia", Medication.VACCIN, 10, 1);

    }


    @Test
    @DisplayName("Test save chart-happy flow")
    public void testSaveChart(){
        MedicalEntity medicalEntity1 = medicalService.modelToEntity(medicalModel1);
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        when(medicalRepository.findById(any())).thenReturn(Optional.empty());
        when(medicalRepository.save(any())).thenReturn(medicalEntity2);

        MedicalEntity result = medicalService.saveMedical(medicalModel2);

        assertEquals(medicalEntity1.getChartDate(), result.getChartDate());
        assertEquals(medicalEntity1.getDisease(),result.getDisease());
        assertEquals(medicalEntity1.getMedication(), result.getMedication());
        assertEquals(medicalEntity1.getAnimalEntity().getIdAnimal(), result.getAnimalEntity().getIdAnimal());
        assertEquals(medicalEntity1.getVetEntity().getIdVet(), result.getVetEntity().getIdVet());
        assertEquals(1, result.getIdMedical());

        verify(medicalRepository).findById(any());
        verify(medicalRepository).save(any());

    }

    @Test
    @DisplayName("Can't save chart")
    void testSaveChartException(){
        MedicalEntity medicalEntity1 = medicalService.modelToEntity(medicalModel1);
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        when(medicalRepository.findById(any())).thenReturn(Optional.of(medicalEntity2));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> medicalService.saveMedical(medicalModel2));

        verify(medicalRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Test get chart by id")
    public void testGetChart(){
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        doReturn(Optional.empty()).when(medicalRepository).findById(medicalEntity2.getIdMedical());
        medicalService.getChartById(medicalEntity2.getIdMedical());
    }

    @Test
    public void testRemoveChart(){
        MedicalEntity medicalEntity2 = medicalService.modelToEntity(medicalModel2);
        lenient().when(medicalRepository.findById(medicalEntity2.getIdMedical())).thenReturn(Optional.of(medicalEntity2));
        verify(medicalRepository, times(0)).deleteById(medicalEntity2.getIdMedical());


    }

    @Test
    public void testNoChartToRemove(){
        when(medicalRepository.findById(any())).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> medicalService.removeChart(medicalModel2.getIdMedical()));
        verify(medicalRepository, times(0)).deleteById(any());

    }

}