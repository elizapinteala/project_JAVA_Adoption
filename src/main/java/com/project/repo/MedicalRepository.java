package com.project.repo;

import com.project.entity.MedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRepository extends JpaRepository<MedicalEntity, Integer> {


}
