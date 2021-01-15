package com.project.repo;

import com.project.entity.AnimalEntity;
import com.project.exception.AnimalException;
import com.project.model.AnimalModel;
import com.project.model.AnimalSpecie;
import com.project.model.AnimalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

    List<AnimalEntity> getAnimalsByBreed(String breed);
    Optional<AnimalEntity> findById(Integer id);
    void deleteByIdAnimal(Integer id);
    void deleteByIdAnimalAndNameAndSpecie(Integer id, String name, AnimalSpecie animalSpecie);
    Optional<AnimalEntity> getAnimalByName(String name);
    List<AnimalEntity> getAnimalsByStatus(AnimalStatus status);
    List<AnimalEntity> getAnimalsBySpecie(AnimalSpecie animalSpecie);
    Optional<AnimalEntity> findByIdAnimal(Integer id);

    @Query(value = "select * from animal where id_animal=:id", nativeQuery = true)
    Iterable<AnimalEntity> findIdAnimal(Integer id);

    AnimalEntity findByName(String nameAnimal);
}
