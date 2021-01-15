package com.project.service;

import com.project.entity.AnimalEntity;
import com.project.exception.AnimalException;
import com.project.model.AnimalModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.repo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    public AnimalModel entityToModel(AnimalEntity animalEntity){
        AnimalModel animalModel = new AnimalModel();
        animalModel.setIdAnimal(animalEntity.getIdAnimal());
        animalModel.setName(animalEntity.getName());
        animalModel.setAge(animalEntity.getAge());
        animalModel.setGender(animalEntity.getGender());
        animalModel.setSpecie(animalEntity.getSpecie());
        animalModel.setBreed(animalEntity.getBreed());
        animalModel.setColor(animalEntity.getColor());
        animalModel.setCheckInDate(animalEntity.getCheckInDate());
        animalModel.setStatus(animalEntity.getStatus());
        animalModel.setAdoptionDate(animalEntity.getAdoptionDate());
        return animalModel;

    }

    public AnimalEntity modelToEntity(AnimalModel animalModel){
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setIdAnimal(animalModel.getIdAnimal());
        animalEntity.setName(animalModel.getName());
        animalEntity.setAge(animalModel.getAge());
        animalEntity.setGender(animalModel.getGender());
        animalEntity.setSpecie(animalModel.getSpecie());
        animalEntity.setBreed(animalModel.getBreed());
        animalEntity.setColor(animalModel.getColor());
        animalEntity.setCheckInDate(animalModel.getCheckInDate());
        animalEntity.setStatus(animalModel.getStatus());
        animalEntity.setAdoptionDate(animalModel.getAdoptionDate());
        return animalEntity;
    }

    @Transactional
    public AnimalEntity saveAnimal(AnimalModel animalModel){
        AnimalEntity animalEntity = new AnimalEntity(animalModel);
        Optional<AnimalEntity> existingAnimal = animalRepository.getAnimalByName(animalModel.getName());
        if(existingAnimal.isPresent() && !existingAnimal.get().getIdAnimal().equals(animalModel.getIdAnimal())){
            throw AnimalException.animalWithSameNameAlreadyExists();
        }
        else {
            if (animalEntity.getStatus().toString().equals(animalEntity.getStatus().WANTED_FOR_ADOPTION.toString())) {
                if (animalEntity.getAdoptionDate() == null) {
                    throw AnimalException.animalCouldNotBeSaved();
                }
            }
            else {
                if (animalEntity.getStatus().toString().equals(animalEntity.getStatus().IN_CENTER.toString())) {
                    if (animalEntity.getAdoptionDate() != null) {
                        throw AnimalException.animalCouldNotBeSaved();
                    }
                }
            }
            /*if(animalEntity.getCheckInDate().isAfter(animalEntity.getAdoptionDate()))
            {
                throw AnimalException.animalCouldNotBeSaved();
            }*/
            return animalRepository.save(animalEntity);
        }

    }

   /* public AnimalEntity saveAnimalBySpecie(AnimalModel animalModel, AnimalSpecie animalSpecie){
        animalModel.setSpecie(animalSpecie);
        AnimalEntity animalEntity = new AnimalEntity(animalModel);

        animalEntity.setName(animalModel.getName());
        animalEntity.setAge(animalModel.getAge());
        animalEntity.setSpecie(animalSpecie);
        animalEntity.setGender(animalModel.getGender());
        animalEntity.setBreed(animalModel.getBreed());
        animalEntity.setColor(animalModel.getColor());
        animalEntity.setCheckInDate(animalModel.getCheckInDate());
        animalEntity.setAdoptionDate(animalModel.getAdoptionDate());

        return animalRepository.save(animalEntity);
    }*/

    public Optional<AnimalEntity> getAnimalById(Integer id){
        Optional<AnimalEntity> oneAnimalEntity = animalRepository.findByIdAnimal(id);
        if(oneAnimalEntity == null){
            throw AnimalException.animalNotFound();
        }
        else {
        return oneAnimalEntity;
        }
    }

 /*public List<AnimalEntity> getAnimalsByStatus(AnimalStatus status){
        List<AnimalEntity> animals = animalRepository.getAnimalsByStatus(status);
       if (animals == null){
           throw AnimalException.animalNotFound();
       }
        else {
           return animals;
       }
   }*/

    /*public List<AnimalEntity> getAnimalsBySpecie(AnimalSpecie animalSpecie){
        List<AnimalEntity> animals = animalRepository.getAnimalsBySpecie(animalSpecie);
        if(animals== null){
            throw AnimalException.animalNotFound();
        }
        else {
            return animals;
        }
    }*/

    public List<AnimalEntity> getAnimals(AnimalStatus animalStatus, AnimalSpecie animalSpecie, String breed){
        List<AnimalEntity> animalEntities = animalRepository.findAll().stream()
                .filter(animal -> isMatch(animal, animalStatus, animalSpecie, breed))
        .collect(Collectors.toList());
        if(animalEntities.isEmpty()){
            throw AnimalException.animalNotFound();
        }
        else {
            return animalEntities.stream().collect(Collectors.toList());}
    }

    private boolean isMatch(AnimalEntity animal, AnimalStatus animalStatus, AnimalSpecie animalSpecie, String breed){
        return(animalStatus==null
        || animal.getStatus().toString().toLowerCase().startsWith(animalStatus.toString().toLowerCase())
        )&&
                (animalSpecie == null
                        || animal.getSpecie().toString().toLowerCase().startsWith(animalSpecie.toString().toLowerCase())
                )&&
                (breed == null
                || animal.getBreed().toLowerCase().startsWith(breed.toLowerCase()));
    }

    /*public List<AnimalEntity> getAnimalsByBreed(String breed){
        List<AnimalEntity> animals = animalRepository.getAnimalsByBreed(breed);
        if(animals == null){
            throw AnimalException.animalNotFound();
        }
        else {
            return animals;}
    }*/

    @Transactional
    public void removeAnimal(Integer id){
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        if(animalEntity.isPresent()){
            animalRepository.deleteByIdAnimal(id);
        }
        else {
            throw AnimalException.animalCouldNotBeRemoved();
        }
    }

    /*public void removeAnimalByIdNameSpecie(Integer id, String name, AnimalSpecie animalSpecie){
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        if(animalEntity.isPresent()){
            animalRepository.deleteByIdAnimalAndNameAndSpecie(id, name, animalSpecie);
        }
        else {
            throw AnimalException.animalCouldNotBeRemoved();
        }


    }*/


    @Transactional
    public AnimalEntity updateAnimal(Integer id, AnimalModel animalModel){
        LocalDate date = null;
        AnimalEntity animalEntity = animalRepository.findByIdAnimal(id).orElse(null);
        if(animalEntity==null){
            return null;
        }
        else {
            animalEntity.setAge(animalModel.getAge());
            animalEntity.setStatus(animalModel.getStatus());
            if(animalEntity.getStatus().toString().equals(animalEntity.getStatus().WANTED_FOR_ADOPTION.toString())) {
                if (animalEntity.getAdoptionDate() == null) {
                    animalEntity.setAdoptionDate(animalModel.getAdoptionDate());
                    return animalRepository.save(animalEntity);
                }
                else {
                    throw AnimalException.animalCouldNotBeSaved();
                }
            }
            else{
                if (animalEntity.getStatus().toString().equals(animalEntity.getStatus().IN_CENTER.toString())) {
                    if (animalEntity.getAdoptionDate() == null) {
                        return animalRepository.save(animalEntity);
                        }
                    else{
                        animalEntity.setAdoptionDate(date);
                        return animalRepository.save(animalEntity);
                    }
                    }
                }
            return animalRepository.save(animalEntity);
        }
    }


    @Transactional
    public void deleteAllAnimals(){
        animalRepository.deleteAll();
    }
}
