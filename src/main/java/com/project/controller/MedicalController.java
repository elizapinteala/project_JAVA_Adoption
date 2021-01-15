package com.project.controller;

import com.project.entity.AnimalEntity;
import com.project.entity.MedicalEntity;
import com.project.exception.AnimalException;
import com.project.model.MedicalModel;
import com.project.service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/medicaly")
public class MedicalController {

    @Autowired
    private MedicalService medicalService;

    public MedicalController(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @PostMapping()
    public ResponseEntity<MedicalEntity> saveChart(@RequestBody MedicalModel medicalModel){
        System.out.println(medicalModel);
        //MedicalEntity medicalEntity= medicalService.modelToEntity(medicalModel);
        MedicalEntity medicalEntity = medicalService.saveMedical(medicalModel);
        return new ResponseEntity<>(medicalEntity, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<MedicalEntity> getChartById(@PathVariable("id") Integer id) {
        Optional<MedicalEntity> medicalEntity = medicalService.getChartById(id);
        if (medicalEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else
            return ResponseEntity.ok(medicalEntity.get());

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeChartById(@RequestParam Integer id) {
        Optional<MedicalEntity> medicalEntity = medicalService.getChartById(id);
        if (medicalEntity.isPresent()) {
            medicalService.removeChart(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
