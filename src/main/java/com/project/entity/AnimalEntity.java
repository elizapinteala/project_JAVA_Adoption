package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animal")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal", insertable = false, updatable = false)
    private Integer idAnimal;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "specie")
    private AnimalSpecie specie;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    private String color;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AnimalStatus status;

    @Column(name = "adoption_date")
    private LocalDate adoptionDate;

    @JsonIgnoreProperties("idAnimal")
    @OneToOne(mappedBy = "idAnimal", cascade = CascadeType.ALL, optional = false)
    private AdoptionEntity adoptionEntity;

    @JsonIgnoreProperties("animalEntity")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal", insertable = false, updatable = false)
    private MedicalEntity medicalEntity;

    public AnimalEntity(){}

    public AnimalEntity(Integer idAnimal, String name, Integer age, Gender gender, AnimalSpecie specie, String breed, String color, LocalDate checkInDate,AnimalStatus status, LocalDate adoptionDate) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.color = color;
        this.checkInDate = checkInDate;
        this.status=status;
        this.adoptionDate = adoptionDate;
    }

    public AnimalEntity(String name, Integer age, Gender gender, AnimalSpecie specie, String breed, String color, LocalDate checkInDate,AnimalStatus status, LocalDate adoptionDate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.color = color;
        this.checkInDate = checkInDate;
        this.status=status;
        this.adoptionDate = adoptionDate;
    }

    public AnimalEntity(AnimalModel animalModel){
        this.idAnimal= animalModel.getIdAnimal();
        this.name= animalModel.getName();
        this.age=animalModel.getAge();
        this.gender=animalModel.getGender();
        this.specie=animalModel.getSpecie();
        this.breed=animalModel.getBreed();
        this.color=animalModel.getColor();
        this.checkInDate=animalModel.getCheckInDate();
        this.status=animalModel.getStatus();
        this.adoptionDate=animalModel.getAdoptionDate();

    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AnimalSpecie getSpecie() {
        return specie;
    }

    public void setSpecie(AnimalSpecie specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public AdoptionEntity getAdoptionEntity() {
        return adoptionEntity;
    }

    public void setAdoptionEntity(AdoptionEntity adoptionEntity) {
        this.adoptionEntity = adoptionEntity;
    }

    public MedicalEntity getMedicalEntity() {
        return medicalEntity;
    }

    public void setMedicalEntity(MedicalEntity medicalEntity) {
        this.medicalEntity = medicalEntity;
    }
}
