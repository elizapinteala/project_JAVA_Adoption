package com.project.controller;

import com.project.entity.PersonEntity;
import com.project.exception.PersonException;
import com.project.model.PersonModel;
import com.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@Valid @RequestBody PersonModel personModel){
        PersonEntity savedPersonEntity = personService.savePerson(personModel);
        return new ResponseEntity<>(savedPersonEntity, HttpStatus.CREATED);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<PersonEntity> getPersonByID(@PathVariable("id") Integer id) {
        Optional<PersonEntity> personEntity = personService.getPersonById(id);
        if (!personEntity.isPresent()) {
            throw PersonException.personNotFound();
        } else
            return ResponseEntity.ok(personEntity.get());

    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<PersonEntity> getPersonByEmail(@PathVariable("email") String email) {
        Optional<PersonEntity> personEntity = personService.getPersonByEmail(email);
        if (personEntity.isEmpty()) {
            throw PersonException.personNotFound();
        } else
            return ResponseEntity.ok(personEntity.get());

    }


    @DeleteMapping("/email")
    public ResponseEntity<Void> removePersonByEmail(@RequestParam String email){
        Optional<PersonEntity> personEntity = personService.getPersonByEmail(email);
        if(personEntity.isEmpty()){
            throw  PersonException.personNotFound();
        }
        else {
            personService.removePersonByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable Integer id, @RequestBody PersonModel personModel){
        PersonEntity personEntity = personService.updatePerson(id, personModel);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }

}
