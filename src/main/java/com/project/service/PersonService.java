package com.project.service;

import com.project.entity.PersonEntity;
import com.project.exception.PersonException;
import com.project.model.PersonModel;
import com.project.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonModel entityToModel(PersonEntity personEntity){
        PersonModel personModel = new PersonModel();
        personModel.setIdPerson(personEntity.getIdPerson());
        personModel.setFirstName(personEntity.getFirstName());
        personModel.setLastName(personEntity.getLastName());
        personModel.setPhone(personModel.getPhone());
        personModel.setEmail(personModel.getEmail());
        return personModel;

    }

    /*private PersonEntity modelToEntity(PersonModel personModel){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setIdPerson(personModel.getIdPerson());
        personEntity.setFirstName(personModel.getFirstName());
        personEntity.setLastName(personModel.getLastName());
        personEntity.setPhone(personModel.getPhone());
        personEntity.setEmail(personModel.getEmail());
        return personEntity;
    }
*/
    @Transactional
    public PersonEntity savePerson(PersonModel personModel){
        PersonEntity personEntity = new PersonEntity(personModel);
        Optional<PersonEntity> existingPerson = personRepository.findByEmail(personModel.getEmail());
        if(existingPerson.isPresent() && !existingPerson.get().getIdPerson().equals(personModel.getIdPerson())){
            throw PersonException.personWithSameEmailAlreadyExists();
        }
        return personRepository.save(personEntity);
    }

    public Optional<PersonEntity> getPersonById(Integer id){

        return personRepository.findById(id);
    }

    public Optional<PersonEntity> getPersonByEmail(String email){
        return personRepository.findByEmail(email);
    }

    @Transactional
    public void removePersonByEmail(String email) {
        Optional<PersonEntity> personEntity = personRepository.findByEmail(email);
        if (personEntity.isPresent()) {
            personRepository.deleteByEmail(email);

        } else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }

    @Transactional
    public PersonEntity updatePerson(Integer id, PersonModel personModel){
        PersonEntity personEntity = personRepository.findById(id).orElse(null);
        if(personEntity==null){
            return null;
        }
        else {
            personEntity.setLastName(personModel.getLastName());
            personEntity.setPhone(personModel.getPhone());
            personEntity.setEmail(personModel.getEmail());
            return personRepository.save(personEntity);
        }
    }


}
