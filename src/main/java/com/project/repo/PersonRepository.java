package com.project.repo;

import com.project.entity.AnimalEntity;
import com.project.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByEmail(String email);

    void deleteByEmail(String email);
    Optional<PersonEntity> findByIdPerson(Integer id);
}
