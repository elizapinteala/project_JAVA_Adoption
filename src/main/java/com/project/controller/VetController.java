package com.project.controller;

import com.project.entity.VetEntity;
import com.project.model.VetModel;
import com.project.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/vet")
public class VetController {

    @Autowired
    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping()
    public ResponseEntity<VetEntity> createVet(@Valid @RequestBody VetModel vetModel){
        VetEntity savedVetEntity= vetService.saveVet(vetModel);
        return new ResponseEntity<>(savedVetEntity, HttpStatus.OK);
    }

    @GetMapping("byLicense/{license}")
    public ResponseEntity<VetEntity> getVetByLicense(@PathVariable("license") String license){
        Optional<VetEntity> vetEntity = vetService.getVetByLicense(license);
        if(vetEntity.isPresent()){
            return ResponseEntity.ok(vetEntity.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<VetEntity> getVetById(@PathVariable("id") Integer id){
        Optional<VetEntity> vetEntity = vetService.getVetById(id);
        if(vetEntity.isPresent()){
            return ResponseEntity.ok(vetEntity.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> removeVet(@RequestParam String license){
        Optional<VetEntity> vetEntity = vetService.getVetByLicense(license);
        if(vetEntity.isPresent()){
            vetService.removeVet(license);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
