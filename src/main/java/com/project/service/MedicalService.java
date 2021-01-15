package com.project.service;

import com.project.entity.AdoptionEntity;
import com.project.entity.AnimalEntity;
import com.project.entity.MedicalEntity;
import com.project.entity.VetEntity;
import com.project.exception.AdoptionException;
import com.project.exception.AnimalException;
import com.project.exception.PersonException;
import com.project.model.MedicalModel;
import com.project.repo.AnimalRepository;
import com.project.repo.MedicalRepository;
import com.project.repo.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MedicalService {

    @Autowired
    private MedicalRepository medicalRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private VetRepository vetRepository;

    public MedicalService(MedicalRepository medicalRepository, AnimalRepository animalRepository, VetRepository vetRepository) {
        this.medicalRepository = medicalRepository;
        this.animalRepository = animalRepository;
        this.vetRepository = vetRepository;
    }

    public MedicalEntity modelToEntity(MedicalModel medicalModel){

        if(medicalModel!=null) {
            MedicalEntity medicalEntity = new MedicalEntity();
            medicalEntity.setIdMedical(medicalModel.getIdMedical());
            medicalEntity.setChartDate(LocalDate.now());
            medicalEntity.setDisease(medicalModel.getDisease());
            medicalEntity.setMedication(medicalModel.getMedication());
            medicalEntity.setAnimalEntity(animalRepository.findByIdAnimal(medicalModel.getIdAnimal()).orElse(null));
            medicalEntity.setVetEntity(vetRepository.findByIdVet(medicalModel.getIdVet()).orElse(null));

            return medicalEntity;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public MedicalEntity saveMedical(MedicalModel medicalModel){
        int ok=0;
        MedicalEntity medicalEntity = modelToEntity(medicalModel);
        if(medicalEntity!=null){
            AnimalEntity animalEntity=animalRepository.findByIdAnimal(medicalModel.getIdAnimal()).orElse(null);
            if(animalEntity!=null){
              VetEntity vetEntity =vetRepository.findByIdVet(medicalModel.getIdVet()).orElse(null);
                if(vetEntity!=null){
                        medicalEntity.setIdMedical(medicalModel.getIdMedical());
                        medicalEntity.setChartDate(LocalDate.now());
                        medicalEntity.setDisease(medicalModel.getDisease());
                        medicalEntity.setMedication(medicalModel.getMedication());
                        medicalEntity.setAnimalEntity(animalRepository.findByIdAnimal(medicalModel.getIdAnimal()).orElse(null));
                        medicalEntity.setVetEntity(vetRepository.findByIdVet(medicalModel.getIdVet()).orElse(null));
                        ok=1;
                }
                else {
                    throw PersonException.personNotFound();
                }
            }
            else {
                    throw AnimalException.animalNotFound();
            }

        }
        else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(ok==1){
            return medicalRepository.save(medicalEntity);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<MedicalEntity> getChartById(Integer id){
        Optional<MedicalEntity> medicalEntity = medicalRepository.findById(id);
        if(medicalEntity == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            return medicalEntity;}
    }

    @Transactional
    public void removeChart(Integer id){
        Optional<MedicalEntity> medicalEntity = medicalRepository.findById(id);
        if(medicalEntity != null){
            medicalRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
