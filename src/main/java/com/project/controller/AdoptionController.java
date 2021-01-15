package com.project.controller;

import com.project.entity.AdoptionEntity;
import com.project.entity.AnimalEntity;
import com.project.exception.AdoptionException;
import com.project.model.AdoptionModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adoption")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping()
    public ResponseEntity<AdoptionEntity> createAdoption(@Valid @RequestBody AdoptionModel adoptionModel){
        AdoptionEntity savedAdoptionEntity = adoptionService.saveAdoption(adoptionModel);
        return new ResponseEntity<>(savedAdoptionEntity, HttpStatus.CREATED);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<AdoptionEntity> getAdoptionById(@PathVariable("id") Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionService.getAdoptionById(id);
        if(adoptionEntity.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            return ResponseEntity.ok(adoptionEntity.get());
        }
    }



    @GetMapping("/byParams")
    public ResponseEntity<Iterable<AdoptionEntity>> getAdoptions(
            @RequestParam(required = false) LocalDate adoptionDate,
            @RequestParam(required = false) Integer idAnimal,
            @RequestParam(required = false) AnimalSpecie animalSpecie,
            @RequestParam(required = false)Integer idPerson){
        List<AdoptionEntity> adoptionEntities = adoptionService.getAdoptions(adoptionDate, idAnimal, animalSpecie, idPerson);
        return adoptionEntities.isEmpty() ?
                ResponseEntity.noContent().build()
                :ResponseEntity.ok(adoptionEntities);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeAdoptionById(@PathVariable("id") Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionService.getAdoptionById(id);
        if(!adoptionEntity.isPresent()){
            throw AdoptionException.adoptionNotFound();
        }
        else
        {
            adoptionService.removeAdoption(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    @DeleteMapping("deleteByIdPerson")
    public ResponseEntity<Void> removeAdoptionByIdPerson(@RequestParam Integer idPerson){
        List<AdoptionEntity> adoptionEntities = adoptionService.getAdoptionsByIdPerson(idPerson);
        if(adoptionEntities==null){
            throw AdoptionException.adoptionNotFound();
        }
        else
        {
            adoptionService.removeAdoptionsByIdPerson(idPerson);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }


}
