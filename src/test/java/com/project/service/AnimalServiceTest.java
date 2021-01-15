package com.project.service;

import com.project.entity.AnimalEntity;
import com.project.entity.PersonEntity;
import com.project.exception.AnimalException;
import com.project.exception.PersonException;
import com.project.model.AnimalModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.model.Gender;
import com.project.repo.AnimalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {
    @Mock
    private AnimalRepository animalRepository;
    @InjectMocks
    private AnimalService animalService;

    private static AnimalModel animalModel1;
    private static AnimalModel animalModel2;
    private static List<AnimalModel> animalModels;
    private static AnimalStatus animalStatus;
    private static AnimalSpecie animalSpecie;
    private static String breed;




    @BeforeAll
    public static void setup(){
        LocalDate checkInDate = LocalDate.now();
        LocalDate adoptionDate = LocalDate.now();
        animalModel1= new AnimalModel("Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate);
        animalModel2= new AnimalModel(1,"Kim",2, Gender.FEMALE, AnimalSpecie.DOG,"Bichon","White",checkInDate, AnimalStatus.IN_CENTER,adoptionDate);
        animalModels = new ArrayList<>();
        animalStatus = AnimalStatus.WANTED_FOR_ADOPTION;
        animalSpecie = AnimalSpecie.CAT;
        breed = "Russian";


    }

   /* @Test
    @DisplayName("Test save animal-happy flow")
    public void testSaveAnimal(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        AnimalEntity savedAnimal = new AnimalEntity(animalModel2);
        when(animalRepository.getAnimalByName(any())).thenReturn(Optional.empty());
        when(animalRepository.save(any())).thenReturn(savedAnimal);

        AnimalEntity result = animalService.saveAnimal(animalService.entityToModel(animalEntity1));

        assertEquals(animalEntity1.getName(), result.getName());
        assertEquals(animalEntity1.getAge(),result.getAge());
        assertEquals(animalEntity1.getGender(), result.getGender());
        assertEquals(animalEntity1.getSpecie(), result.getSpecie());
        assertEquals(animalEntity1.getBreed(), result.getBreed());
        assertEquals(animalEntity1.getColor(), result.getColor());
        assertEquals(1, result.getIdAnimal());

        verify(animalRepository).getAnimalByName(any());
        verify(animalRepository).save(any());

    }*/

    @Test
    @DisplayName("Save animal with same name already exists")
    void testSaveAnimalExceptionName(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        AnimalEntity savedAnimal = new AnimalEntity(animalModel2);
        when(animalRepository.getAnimalByName(any())).thenReturn(Optional.of(savedAnimal));
        AnimalException exception = assertThrows(AnimalException.class,
                ()-> animalService.saveAnimal(animalService.entityToModel(animalEntity1)));


        verify(animalRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Test get animal by Id")
    public void testGetAnimalById(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        doReturn(Optional.empty()).when(animalRepository).findByIdAnimal(animalEntity1.getIdAnimal());
        animalService.getAnimalById(animalEntity1.getIdAnimal());
    }

    @Test
    @DisplayName("Test remove animal by id - happy flow")
    public void testRemoveAnimalById(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        lenient().when(animalRepository.findByIdAnimal(animalEntity1.getIdAnimal())).thenReturn(Optional.of(animalEntity1));
        animalRepository.deleteByIdAnimal(animalEntity1.getIdAnimal());
        verify(animalRepository, times(1)).deleteByIdAnimal(animalEntity1.getIdAnimal());


    }

    @Test
    @DisplayName("Test no animal to remove")
    public void testNoAnimalToRemove(){
        lenient().when(animalRepository.findByIdAnimal(any())).thenReturn(Optional.empty());
        AnimalException exception = assertThrows(AnimalException.class,
                ()-> animalService.removeAnimal(any()));
        verify(animalRepository, times(0)).deleteByIdAnimal(any());

    }

    @Test
    @DisplayName("Test update animal")
    public void testUpdateAnimal(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        lenient().when(animalRepository.findByIdAnimal(animalEntity1.getIdAnimal())).thenReturn(Optional.of(animalEntity1));
        AnimalEntity result = animalService.updateAnimal(animalEntity1.getIdAnimal(), animalService.entityToModel(animalEntity1));

    }

    @Test
    @DisplayName("Test animals not found")
    public void testGetNoAnimals(){
        AnimalEntity animalEntity1 = new AnimalEntity(animalModel1);
        List<AnimalEntity> animalEntityList = new ArrayList<>();
        animalEntityList = animalModels.stream().map(animalModel -> animalService.modelToEntity(animalModel))
                .collect(Collectors.toList());
        when(animalRepository.findAll()).thenReturn(animalEntityList);
        AnimalException exception = assertThrows(AnimalException.class, () ->
                animalService.getAnimals(animalStatus, animalSpecie, breed));
        assertEquals(AnimalException.AnimalErrors.ANIMAL_NOT_FOUND, exception.getError());


    }
}