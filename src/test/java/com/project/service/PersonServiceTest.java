package com.project.service;

import com.project.entity.PersonEntity;;
import com.project.exception.PersonException;
import com.project.model.PersonModel;
import com.project.repo.PersonRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    private static PersonModel personModel;
    private static PersonModel personModel1;
    @BeforeAll
    public static void setup(){
        personModel = new PersonModel("Eliza","Pinteala","0756932885","elizapinteala@gmail.com");
        personModel1 = new PersonModel(1, "Eliza","Pinteala", "0756932885","elizapinteala@gmail.com");

    }



    @Test
    @DisplayName("Test save person-happy flow")
    public void testSavePerson(){
        PersonEntity personEntity = new PersonEntity(personModel);
        PersonEntity savedPerson = new PersonEntity(personModel1);
        when(personRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(personRepository.save(any())).thenReturn(savedPerson);

        PersonEntity result = personService.savePerson(personService.entityToModel(personEntity));

        assertEquals(personEntity.getFirstName(), result.getFirstName());
        assertEquals(personEntity.getLastName(),result.getLastName());
        assertEquals(personEntity.getPhone(), result.getPhone());
        assertEquals(personEntity.getEmail(), result.getEmail());
        assertEquals(1, result.getIdPerson());

        verify(personRepository).findByEmail(any());
        verify(personRepository).save(any());

    }

    @Test
    @DisplayName("Save person with same email already exists")
    void testSavePersonException(){
        PersonEntity personEntity = new PersonEntity(personModel);
        PersonEntity savedPerson = new PersonEntity(personModel1);
        when(personRepository.findByEmail(any())).thenReturn(Optional.of(savedPerson));
        PersonException exception = assertThrows(PersonException.class,
                ()-> personService.savePerson(personService.entityToModel(personEntity)));

        verify(personRepository, times(0)).save(any());
    }



    @Test
    @DisplayName("Test get person by Id")
    public void testGetPersonById(){
        PersonEntity personEntity = new PersonEntity(personModel);
        doReturn(Optional.empty()).when(personRepository).findById(personEntity.getIdPerson());
        personService.getPersonById(personEntity.getIdPerson());
    }

    @Test
    @DisplayName("Test get person by email")
    public void testGetPersonByEmail(){
        PersonEntity personEntity = new PersonEntity(personModel);
        doReturn(Optional.empty()).when(personRepository).findByEmail(personEntity.getEmail());
        personService.getPersonByEmail(personEntity.getEmail());
    }

    @Test
    @DisplayName("Test remove person by email - happy flow")
    public void testRemovePersonByEmail(){
        PersonEntity personEntity = new PersonEntity(personModel);
        String email = personEntity.getEmail();
        lenient().when(personRepository.findByEmail(email)).thenReturn(Optional.of(personEntity));
        personRepository.deleteByEmail(email);
        verify(personRepository, times(1)).deleteByEmail(email);

    }


    @Test
    @DisplayName("Test no person to remove")
    public void testNoPersonToRemove(){
        when(personRepository.findByEmail(any())).thenReturn(Optional.empty());
        PersonException exception = assertThrows(PersonException.class,
                ()-> personService.removePersonByEmail(any()));
        verify(personRepository, times(0)).deleteByEmail(any());

    }

    @Test
    @DisplayName("Test no updated person")
    public void testNoUpdatedPerson(){
        PersonEntity personEntity = new PersonEntity(personModel);
        lenient().when(personRepository.findByIdPerson(0)).thenReturn(Optional.empty());
        lenient().when(personRepository.save(any())).thenReturn(null);

    }


    @Test
    @DisplayName("Test update person")
    public void testUpdatePerson(){
       /*PersonEntity personEntity = new PersonEntity(personModel);
        lenient().when(personRepository.findByEmail(personEntity.getEmail())).thenReturn(Optional.of(personEntity));
        PersonEntity result = personService.updatePerson(personEntity.getIdPerson(), personService.entityToModel(personEntity));*/

        PersonEntity personEntity = new PersonEntity(personModel1);
        PersonEntity savedPerson = new PersonEntity(personModel1);
        savedPerson.setLastName(personModel1.getLastName());
        savedPerson.setPhone(personModel1.getPhone());
        savedPerson.setEmail(personModel1.getEmail());
        when(personRepository.save(any())).thenReturn(savedPerson);

        PersonEntity result = personService.savePerson(personService.entityToModel(savedPerson));

        assertEquals(personEntity.getFirstName(), result.getFirstName());
        assertEquals(personEntity.getLastName(),result.getLastName());
        assertEquals(personEntity.getPhone(), result.getPhone());
        assertEquals(personEntity.getEmail(), result.getEmail());
        assertEquals(1, result.getIdPerson());

        verify(personRepository).findByEmail(any());
        verify(personRepository).save(any());
    }

}