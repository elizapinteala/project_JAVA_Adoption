package com.project.service;

import com.project.entity.AdminEntity;
import com.project.entity.PersonEntity;
import com.project.exception.PersonException;
import com.project.model.AdminModel;
import com.project.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminModel entityToModel(AdminEntity adminEntity){
        AdminModel adminModel = new AdminModel();
        adminModel.setIdAdmin(adminEntity.getIdAdmin());
        adminModel.setFirstName(adminEntity.getFirstName());
        adminModel.setLastName(adminEntity.getLastName());
        adminModel.setEmail(adminEntity.getEmail());
        return adminModel;
    }

    /*public AdminEntity modelToEntity(AdminModel adminModel){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setIdAdmin(adminModel.getIdAdmin());
        adminEntity.setFirstName(adminModel.getFirstName());
        adminEntity.setLastName(adminModel.getLastName());
        adminEntity.setEmail(adminModel.getEmail());
        return adminEntity;
    }*/

    @Transactional
    public AdminEntity saveAdmin(AdminModel adminModel){
        AdminEntity adminEntity = new AdminEntity(adminModel) ;
        Optional<AdminEntity> existingAdmin = adminRepository.findByEmail(adminModel.getEmail());
        if(existingAdmin.isPresent() &&!existingAdmin.get().getIdAdmin().equals(adminModel.getIdAdmin())){
            throw PersonException.personWithSameEmailAlreadyExists();
        }
        return adminRepository.save(adminEntity);
    }

    public Optional<AdminEntity> getAdmin(String email){
        return adminRepository.findByEmail(email);
    }

    @Transactional
    public void deleteAdmin(String email){
        Optional<AdminEntity> adminEntity = adminRepository.findByEmail(email);
        if (adminEntity.isPresent()) {
            adminRepository.deleteByEmail(email);

        } else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }
}
