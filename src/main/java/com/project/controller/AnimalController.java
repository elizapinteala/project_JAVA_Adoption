package com.project.controller;

import com.project.entity.AnimalEntity;
import com.project.exception.AnimalException;
import com.project.model.AnimalModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import com.project.service.AnimalService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }


    @PostMapping()
    public ResponseEntity<AnimalEntity> createAnimal(@Valid @RequestBody AnimalModel animalModel){
        AnimalEntity savedAnimalEntity = animalService.saveAnimal(animalModel);
        return new ResponseEntity<>(savedAnimalEntity, HttpStatus.CREATED);
    }


    /*@Transactional
    @PostMapping("/dog")
    public ResponseEntity<AnimalEntity> createDog(@RequestBody @Valid AnimalModel animalModel) {
        AnimalEntity savedAnimalEntity = animalService.saveAnimalBySpecie(animalModel, AnimalSpecie.DOG);
        return ResponseEntity.ok().body(savedAnimalEntity);
    }

    @Transactional
    @PostMapping("/cat")
    public ResponseEntity<AnimalEntity> createCat(@RequestBody @Valid AnimalModel animalModel) {
        AnimalEntity savedAnimalEntity = animalService.saveAnimalBySpecie(animalModel, AnimalSpecie.CAT);
        return ResponseEntity.ok().body(savedAnimalEntity);
    }

    @Transactional
    @PostMapping("/{other}")
    public ResponseEntity<AnimalEntity> createOther(@RequestBody @Valid AnimalModel animalModel, @PathVariable AnimalSpecie animalSpecie) {
        AnimalEntity savedAnimalEntity = animalService.saveAnimalBySpecie(animalModel, AnimalSpecie.OTHER);
        return ResponseEntity.ok().body(savedAnimalEntity);
    }*/

    @GetMapping("/byId/{id}")
    public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable("id") Integer id) {
        Optional<AnimalEntity> animalEntity = animalService.getAnimalById(id);
        if (animalEntity.isEmpty()) {
            throw AnimalException.animalNotFound();
        } else
            return ResponseEntity.ok(animalEntity.get());

    }

    @GetMapping("/byParams")
    public ResponseEntity<Iterable<AnimalEntity>> getAnimals(
            @RequestParam(required = false)AnimalStatus animalStatus,
            @RequestParam(required = false)AnimalSpecie animalSpecie,
            @RequestParam(required = false)String breed){
        List<AnimalEntity> animalEntities = animalService.getAnimals(animalStatus, animalSpecie, breed);
        return animalEntities.isEmpty() ?
                ResponseEntity.noContent().build()
                :ResponseEntity.ok(animalEntities);
    }




    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeAnimalById(@RequestParam Integer id) {
        Optional<AnimalEntity> animalEntity = animalService.getAnimalById(id);
        if (animalEntity.isPresent()) {
            animalService.removeAnimal(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            throw AnimalException.animalCouldNotBeRemoved();
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<AnimalEntity> updateAnimal(@PathVariable Integer id, @RequestBody AnimalModel animalModel) {
        AnimalEntity animalEntity = animalService.updateAnimal(id, animalModel);
        /*if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }*/
        return new ResponseEntity<>(animalEntity, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllAnimals(){
        animalService.deleteAllAnimals();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
