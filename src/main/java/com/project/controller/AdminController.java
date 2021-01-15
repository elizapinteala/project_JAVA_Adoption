package com.project.controller;

import com.project.entity.AdminEntity;
import com.project.exception.PersonException;
import com.project.model.AdminModel;
import com.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    public ResponseEntity<AdminEntity> createAdmin(@Valid @RequestBody AdminModel adminModel){
        AdminEntity savedAdminEntity = adminService.saveAdmin(adminModel);
        return new ResponseEntity<>(savedAdminEntity, HttpStatus.CREATED);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<AdminEntity> getAdmin(@PathVariable("email") String email) {
        Optional<AdminEntity> adminEntity = adminService.getAdmin(email);
        if (adminEntity.isEmpty()) {
            throw PersonException.personNotFound();
        } else
            return ResponseEntity.ok(adminEntity.get());

    }

    @DeleteMapping("/email")
    public ResponseEntity<Void> removeAdmin(@RequestParam String email){
        Optional<AdminEntity> adminEntity = adminService.getAdmin(email);
        if(!adminEntity.isPresent()){
            throw  PersonException.personNotFound();
        }
        else {
            adminService.deleteAdmin(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }
}
