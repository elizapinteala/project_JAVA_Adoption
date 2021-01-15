package com.project.repo;

import com.project.entity.AdoptionEntity;
import com.project.entity.AnimalEntity;
import com.project.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, Integer> {
    Optional<AdoptionEntity> findByIdAnimal(AnimalEntity idAdnimal);
    List<AdoptionEntity> findAdoptionEntitiesByIdPerson(PersonEntity idPerson);
    List<AdoptionEntity> findAdoptionEntitiesByAdoptionDate(LocalDate adoptionDate);


}
