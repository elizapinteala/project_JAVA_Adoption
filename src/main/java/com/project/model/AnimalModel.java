package com.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.validators.OnlyLetters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


public class AnimalModel {


    private Integer idAnimal;

    @OnlyLetters
    @NotNull(message = "Please set the name of the animal.")
    private String name;


    @Min(0)
    @NotNull(message = "Please set the age of the animal.")
    private Integer age;

    @NotNull(message = "Please set the gender of the animal.")
    private Gender gender;

    @NotNull(message = "Please set the specie of the animal.")
    private AnimalSpecie specie;

    @NotNull(message = "Please set the breed of the animal.")
    private String breed;


    @NotNull(message = "Please set the color of the animal.")
    private String color;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Please set the check in date of the animal.")
    private LocalDate checkInDate;

    @NotNull(message = "Please set the status of the animal.")
    private AnimalStatus status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate adoptionDate;

    private Integer idAdoption;

    private Integer idMedicalChart;


    public AnimalModel(){}


    public AnimalModel(Integer idAnimal, @NotNull(message = "Please set the name of the animal.") String name, @Min(0) @NotNull(message = "Please set the age of the animal.") Integer age, @NotNull(message = "Please set the gender of the animal.") Gender gender, @NotNull(message = "Please set the specie of the animal.") AnimalSpecie specie, @NotNull(message = "Please set the breed of the animal.") String breed, @NotNull(message = "Please set the color of the animal.") String color, @NotNull(message = "Please set the check in date of the animal.") LocalDate checkInDate, @NotNull(message = "Please set the status of the animal.") AnimalStatus status, LocalDate adoptionDate) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.color = color;
        this.checkInDate = checkInDate;
        this.status = status;
        this.adoptionDate = adoptionDate;
    }

    public AnimalModel(@NotNull(message = "Please set the name of the animal.") String name, @Min(0) @NotNull(message = "Please set the age of the animal.") Integer age, @NotNull(message = "Please set the gender of the animal.") Gender gender, @NotNull(message = "Please set the specie of the animal.") AnimalSpecie specie, @NotNull(message = "Please set the breed of the animal.") String breed, @NotNull(message = "Please set the color of the animal.") String color, @NotNull(message = "Please set the check in date of the animal.") LocalDate checkInDate, @NotNull(message = "Please set the status of the animal.") AnimalStatus status, LocalDate adoptionDate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.color = color;
        this.checkInDate = checkInDate;
        this.status = status;
        this.adoptionDate = adoptionDate;
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

    public Integer getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(Integer idAdoption) {
        this.idAdoption = idAdoption;
    }

    public Integer getIdMedicalChart() {
        return idMedicalChart;
    }

    public void setIdMedicalChart(Integer idMedicalChart) {
        this.idMedicalChart = idMedicalChart;
    }

    @Override
    public String toString() {
        return "AnimalModel{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", specie=" + specie +
                ", breed='" + breed + '\'' +
                ", color='" + color + '\'' +
                ", checkInDate=" + checkInDate +
                ", status=" + status +
                ", adoptionDate=" + adoptionDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalModel)) return false;
        AnimalModel that = (AnimalModel) o;
        return idAnimal.equals(that.idAnimal) &&
                name.equals(that.name) &&
                age.equals(that.age) &&
                gender == that.gender &&
                specie == that.specie &&
                breed.equals(that.breed) &&
                Objects.equals(color, that.color) &&
                checkInDate.equals(that.checkInDate) &&
                status == that.status &&
                Objects.equals(adoptionDate, that.adoptionDate) &&
                Objects.equals(idAdoption, that.idAdoption) &&
                Objects.equals(idMedicalChart, that.idMedicalChart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, name, age, gender, specie, breed, color, checkInDate, status, adoptionDate, idAdoption, idMedicalChart);
    }
}

