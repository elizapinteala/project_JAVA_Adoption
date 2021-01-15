package com.project.service;

import com.project.entity.PersonEntity;
import com.project.entity.VetEntity;
import com.project.model.PersonModel;
import com.project.model.VetModel;
import com.project.repo.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public VetModel entityToModel(VetEntity vetEntity){
        VetModel vetModel = new VetModel();
        vetModel.setIdVet(vetEntity.getIdVet());
        vetModel.setLicense(vetEntity.getLicense());
        vetModel.setFirstName(vetEntity.getFirstName());
        vetModel.setLastName(vetEntity.getLastName());
        vetModel.setEmail(vetEntity.getEmail());

        return vetModel;

    }
    @Transactional
    public VetEntity saveVet(VetModel vetModel){
        VetEntity vetEntity = new VetEntity(vetModel);
        Optional<VetEntity> existingVet = vetRepository.findByLicense(vetModel.getLicense());
        if(existingVet.isPresent() && !existingVet.get().getIdVet().equals(vetModel.getIdVet())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        else {
            return vetRepository.save(vetEntity);
        }
    }

    public Optional<VetEntity> getVetByLicense(String license){
        Optional<VetEntity> oneVetEntity = vetRepository.findByLicense(license);
        if(oneVetEntity == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            return oneVetEntity;
        }
    }

    public Optional<VetEntity> getVetById(Integer id){
        Optional<VetEntity> oneVetEntity = vetRepository.findByIdVet(id);
        if(oneVetEntity == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            return oneVetEntity;
        }
    }

    @Transactional
    public void removeVet(String license){
        Optional<VetEntity> vetEntity = vetRepository.findByLicense(license);
        if(vetEntity.isPresent()){
            vetRepository.deleteByLicense(license);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }




}
