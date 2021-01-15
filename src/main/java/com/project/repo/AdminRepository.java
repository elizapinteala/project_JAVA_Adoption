package com.project.repo;

import com.project.entity.AdminEntity;
import com.project.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByEmail(String email);

    void deleteByEmail(String email);
    Optional<AdminEntity> findByIdAdmin(Integer id);

}
