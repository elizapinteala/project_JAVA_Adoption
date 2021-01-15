package com.project.service;

import com.project.entity.AdoptionEntity;
import com.project.entity.PersonEntity;
import com.project.exception.AdoptionException;
import com.project.exception.PersonException;
import com.project.model.AdoptionModel;
import com.project.model.AnimalSpecie;
import com.project.model.PersonModel;
import com.project.repo.AdminRepository;
import com.project.repo.AdoptionRepository;
import com.project.repo.AnimalRepository;
import com.project.repo.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdoptionServiceTest {

    @Mock
    private AdoptionRepository adoptionRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AdoptionService adoptionService;

    private static AdoptionModel adoptionModel1;
    private static AdoptionModel adoptionModel2;

    @BeforeAll
    public static void setup() {
        LocalDate adoptionDate = LocalDate.now();
        adoptionModel1 = new AdoptionModel(adoptionDate, 1, AnimalSpecie.DOG, 1, 1);
        adoptionModel2 = new AdoptionModel(1, adoptionDate, 1, AnimalSpecie.DOG, 1, 1);

    }

    /*@Test
    @DisplayName("Test save adoption-happy flow")
    public void testSaveAdoption(){
        AdoptionEntity adoptionEntity1 = adoptionService.modelToEntity(adoptionModel1);
        AdoptionEntity adoptionEntity2 = adoptionService.modelToEntity(adoptionModel2);
        when(personRepository.findByIdPerson(any())).thenReturn(Optional.empty());
        when(adminRepository.findByIdAdmin(any())).thenReturn(Optional.empty());
        when(animalRepository.findByIdAnimal(any())).thenReturn(Optional.empty());

        AdoptionEntity result = adoptionService.saveAdoption(adoptionService.entityToModel(adoptionEntity2));

        assertEquals(adoptionEntity1.getAdoptionDate(), result.getAdoptionDate());
        assertEquals(adoptionEntity1.getIdAnimal().getIdAnimal(),result.getIdAnimal().getIdAnimal());
        assertEquals(adoptionEntity1.getAnimalSpecie(), result.getAnimalSpecie());
        assertEquals(adoptionEntity1.getIdPerson().getIdPerson(), result.getIdPerson().getIdPerson());
        assertEquals(adoptionEntity1.getIdAdmin().getIdAdmin(),result.getIdAdmin().getIdAdmin());
        assertEquals(1, result.getIdPerson());

        verify(personRepository).findByIdPerson(any());
        verify(adminRepository).findByIdAdmin(any());
        verify(animalRepository).findByIdAnimal(any());
        verify(adoptionRepository).save(any());

    }*/

    /*@Test
    @DisplayName("Can't save adoption")
    void testSaveAdoptionException(){
        AdoptionEntity adoptionEntity1 = adoptionService.modelToEntity(adoptionModel1);
        AdoptionEntity adoptionEntity2 = adoptionService.modelToEntity(adoptionModel2);
        when(personRepository.findByIdPerson(0)).thenReturn(Optional.empty());
        when(adminRepository.findByIdAdmin(0)).thenReturn(Optional.empty());
        when(animalRepository.findByIdAnimal(0)).thenReturn(Optional.empty());
        AdoptionException exception = assertThrows(AdoptionException.class,
                ()-> adoptionService.saveAdoption(adoptionService.entityToModel(adoptionEntity1)));

        verify(personRepository, times(0)).save(any());
    }*/

    @Test
    @DisplayName("Test get adoption by Id")
    public void testGetAdoptionById() {
        AdoptionEntity adoptionEntity = adoptionService.modelToEntity(adoptionModel2);
        lenient().doReturn(Optional.empty()).when(adminRepository).findById(adoptionEntity.getIdAdoption());
        adoptionService.getAdoptionById(adoptionEntity.getIdAdoption());
    }

    /*@Test
    @DisplayName("Test get adoption by IdPerson")
    public void testGetAdoptionByIdPerson(){
        AdoptionEntity adoptionEntity = adoptionService.modelToEntity(adoptionModel2);
        lenient().doReturn(Optional.empty()).when(adminRepository).findById(adoptionModel2.getIdPerson());
        adoptionService.getAdoptionsByIdPerson(adoptionModel2.getIdPerson());
    }
*/

    @Test
    @DisplayName("Test remove adoption by id - happy flow")
    public void testRemoveAdoptionById(){
        AdoptionEntity adoptionEntity = adoptionService.modelToEntity(adoptionModel2);
        Integer idAdoption = adoptionEntity.getIdAdoption();
        lenient().when(adoptionRepository.findById(idAdoption)).thenReturn(Optional.of(adoptionEntity));
        adoptionRepository.deleteById(idAdoption);
        verify(adoptionRepository, times(1)).deleteById(idAdoption);

    }


    @Test
    @DisplayName("Test no person to remove")
    public void testNoPersonToRemove(){
        lenient().when(adoptionRepository.findById(any())).thenReturn(Optional.empty());
       // AdoptionException exception = assertThrows(AdoptionException.class,
        //        ()-> adoptionService.removeAdoption(any()));
        //assertEquals(AdoptionException.AdoptionErrors.ADOPTION_COULD_NOT_BE_REMOVED, exception.getError());
        verify(adoptionRepository, times(0)).deleteById(any());

    }

}