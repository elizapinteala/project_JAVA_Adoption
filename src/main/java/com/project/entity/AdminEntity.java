package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.AdminModel;
import com.project.service.AdminService;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin", insertable = false, updatable = false)
    private Integer idAdmin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;


    @JsonIgnoreProperties("idAdmin")
    @OneToMany(mappedBy = "idAdmin",cascade = CascadeType.ALL)
    private List<AdoptionEntity> adoptions;

    public AdminEntity(){}

    public AdminEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public AdminEntity(Integer idAdmin, String firstName, String lastName, String email) {
        this.idAdmin = idAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public AdminEntity(AdminModel adminModel){
        this.idAdmin=adminModel.getIdAdmin();
        this.firstName=adminModel.getFirstName();
        this.lastName=adminModel.getLastName();
        this.email=adminModel.getEmail();
    }


    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AdoptionEntity> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<AdoptionEntity> adoptions) {
        this.adoptions = adoptions;
    }
}
