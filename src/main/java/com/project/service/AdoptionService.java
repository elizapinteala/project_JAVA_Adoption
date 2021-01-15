package com.project.service;

import com.project.entity.AdminEntity;
import com.project.entity.AdoptionEntity;
import com.project.entity.AnimalEntity;
import com.project.entity.PersonEntity;
import com.project.exception.AdoptionException;
import com.project.exception.AnimalException;
import com.project.model.AdoptionModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.repo.AdminRepository;
import com.project.repo.AdoptionRepository;
import com.project.repo.AnimalRepository;
import com.project.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdoptionService {

    private static final Logger log = LoggerFactory.getLogger(AdoptionService.class);
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AdminRepository adminRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, AnimalRepository animalRepository, PersonRepository personRepository, AdminRepository adminRepository) {
        this.adoptionRepository = adoptionRepository;
        this.animalRepository = animalRepository;
        this.personRepository = personRepository;
        this.adminRepository = adminRepository;
    }

    public AdoptionModel entityToModel(AdoptionEntity adoptionEntity){
        AdoptionModel adoptionModel = new AdoptionModel();
        adoptionModel.setIdAdoption(adoptionEntity.getIdAdoption());
        adoptionModel.setAdoptionDate(adoptionEntity.getAdoptionDate());
        adoptionModel.setIdAnimal(adoptionEntity.getIdAnimal().getIdAnimal());
        adoptionModel.setAnimalSpecie(adoptionEntity.getAnimalSpecie());
        adoptionModel.setIdPerson(adoptionEntity.getIdPerson().getIdPerson());
        adoptionModel.setIdAdmin(adoptionEntity.getIdAdmin().getIdAdmin());
        return adoptionModel;

    }

    public AdoptionEntity modelToEntity(AdoptionModel adoptionModel){
        AdoptionEntity adoptionEntity = new AdoptionEntity();
        adoptionEntity.setIdAdoption(adoptionModel.getIdAdoption());
        adoptionEntity.setAdoptionDate(adoptionModel.getAdoptionDate());
        adoptionEntity.setIdAnimal(animalRepository.findByIdAnimal(adoptionModel.getIdAnimal()).orElse(null));
        adoptionEntity.setAnimalSpecie(adoptionModel.getAnimalSpecie());
        adoptionEntity.setIdPerson(personRepository.findByIdPerson(adoptionModel.getIdPerson()).orElse(null));
        adoptionEntity.setIdAdmin(adminRepository.findByIdAdmin(adoptionModel.getIdAdmin()).orElse(null));
        return adoptionEntity;
    }

    @Transactional
    public AdoptionEntity saveAdoption(AdoptionModel adoptionModel) {
        int ok = 0;
        AdoptionEntity adoptionEntity = modelToEntity(adoptionModel);
        if (adoptionEntity != null) {
            PersonEntity personEntity = personRepository.findByIdPerson(adoptionModel.getIdPerson()).orElse(null);
            if (personEntity != null) {
                AdminEntity adminEntity = adminRepository.findByIdAdmin(adoptionModel.getIdAdmin()).orElse(null);
                if (adminEntity != null) {
                    AnimalEntity animalEntity = animalRepository.findByIdAnimal(adoptionModel.getIdAnimal()).orElse(null);
                    if (animalEntity.getStatus() == AnimalStatus.IN_CENTER) {
                        animalEntity.setStatus(AnimalStatus.WANTED_FOR_ADOPTION);
                        animalEntity.setAdoptionDate(adoptionEntity.getAdoptionDate());
                        ok = 1;
                    }
                    else {
                        throw AdoptionException.animalIsAlreadyAdopted();
                    }
                }
                else {
                    throw AdoptionException.adminNotFound();
                }
            }
            else {
                    throw AdoptionException.personNotFound();
                }
            }
        else {
            throw AnimalException.animalNotFound();
        }
        if(ok==1){

            return adoptionRepository.save(adoptionEntity);
        }
        else {
            throw AdoptionException.adoptionCouldNotBeSaved();
        }
    }


    public Optional<AdoptionEntity> getAdoptionById(Integer id){
        Optional<AdoptionEntity> oneAdoptionEntity = adoptionRepository.findById(id);
        if(oneAdoptionEntity == null){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            return oneAdoptionEntity;}
    }

    public List<AdoptionEntity> getAdoptionsByIdPerson(Integer id){
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        List<AdoptionEntity> adoptionEntities = adoptionRepository.findAdoptionEntitiesByIdPerson(personEntity.get());
        if(adoptionEntities == null){
            throw AdoptionException.adoptionNotFound();
        }
        else{
            return adoptionEntities;
        }
    }


    public List<AdoptionEntity> getAdoptions( LocalDate adoptionDate, Integer idAnimal, AnimalSpecie animalSpecie, Integer idPerson){
        List<AdoptionEntity> adoptionEntities = adoptionRepository.findAll().stream()
                .filter(adoption -> isMatch(adoption, adoptionDate,idAnimal, animalSpecie, idPerson))
                .collect(Collectors.toList());
        if(adoptionEntities.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            return adoptionEntities.stream().collect(Collectors.toList());}
    }

    private boolean isMatch(AdoptionEntity adoption,LocalDate adoptionDate,Integer idAnimal, AnimalSpecie animalSpecie, Integer idPerosn){
        return(adoptionDate==null
                || adoption.getAdoptionDate().toString().equals(adoptionDate.toString())
        )&&
                (idAnimal == null
                        || adoption.getIdAnimal().getIdAnimal().equals(idAnimal)
                )&&
                (animalSpecie == null
                        || adoption.getAnimalSpecie().toString().toLowerCase().startsWith(animalSpecie.toString().toLowerCase())
                )&&
                (idPerosn == null
                || adoption.getIdPerson().getIdPerson().equals(idPerosn));
    }

    @Transactional
    public void removeAdoption(Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionRepository.findById(id);
        if(adoptionEntity != null){
            adoptionRepository.deleteById(id);
        }
        else {
            throw AdoptionException.adoptionCouldNotBeRemoved();
        }
    }


    @Transactional
    public void removeAdoptionsByIdPerson(Integer id){
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        List<AdoptionEntity> adoptionEntities = adoptionRepository.findAdoptionEntitiesByIdPerson(personEntity.get());
        if(adoptionEntities == null){
            throw AdoptionException.adoptionCouldNotBeRemoved();
        }
        else {
            adoptionRepository.deleteInBatch(adoptionEntities);
        }
    }


}
