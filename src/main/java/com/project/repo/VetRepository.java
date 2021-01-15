package com.project.repo;

import com.project.entity.AnimalEntity;
import com.project.entity.VetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<VetEntity, Integer> {
    Optional<VetEntity> findById(Integer id);
    Optional<VetEntity> findByLicense(String license);
    Optional<VetEntity> findByIdVet(Integer id);

    @Query(value = "select * from vet where id_vet=:id", nativeQuery = true)
    Iterable<VetEntity> findIdVet(Integer id);

    void deleteByLicense(String license);
}
