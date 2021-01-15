package com.project.entity;

import com.project.model.VetModel;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vet")
public class VetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vet", insertable = false, updatable = false)
    private Integer idVet;

    @Column(name = "license", unique = true)
    private String license;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "vetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalEntity> medicalEntity;

    public VetEntity(){}

    public VetEntity(Integer idVet, String license, String firstName, String lastName, String email) {
        this.idVet = idVet;
        this.license = license;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public VetEntity(String license, String firstName, String lastName, String email) {
        this.license = license;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public VetEntity(VetModel vetModel){
        this.idVet = vetModel.getIdVet();
        this.license= vetModel.getLicense();
        this.firstName=vetModel.getFirstName();
        this.lastName=vetModel.getLastName();
        this.email=vetModel.getEmail();
    }

    public Integer getIdVet() {
        return idVet;
    }

    public void setIdVet(Integer idVet) {
        this.idVet = idVet;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
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

    public List<MedicalEntity> getMedicalEntity() {
        return medicalEntity;
    }

    public void setMedicalEntity(List<MedicalEntity> medicalEntity) {
        this.medicalEntity = medicalEntity;
    }
}
