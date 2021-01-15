package com.project.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


public class AdoptionModel {


    private Integer idAdoption;

    @NotNull(message = "Please set the adoption date.")
    private LocalDate adoptionDate;

    @NotNull
    private Integer idAnimal;

    @NotNull
    private AnimalSpecie animalSpecie;

    @NotNull
    private Integer idPerson;

    @NotNull
    private Integer idAdmin;

    public AdoptionModel(){}

    public AdoptionModel(Integer idAdoption, @NotNull LocalDate adoptionDate, @NotNull Integer idAnimal,@NotNull AnimalSpecie animalSpecie, @NotNull Integer idPerson, @NotNull Integer idAdmin) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.idAnimal = idAnimal;
        this.animalSpecie=animalSpecie;
        this.idPerson = idPerson;
        this.idAdmin = idAdmin;
    }

    public AdoptionModel(@NotNull LocalDate adoptionDate, @NotNull Integer idAnimal,@NotNull AnimalSpecie animalSpecie, @NotNull Integer idPerson, @NotNull Integer idAdmin) {
        this.adoptionDate = adoptionDate;
        this.idAnimal = idAnimal;
        this.animalSpecie=animalSpecie;
        this.idPerson = idPerson;
        this.idAdmin = idAdmin;
    }

    public Integer getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(Integer idAdoption) {
        this.idAdoption = idAdoption;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public AnimalSpecie getAnimalSpecie() {
        return animalSpecie;
    }

    public void setAnimalSpecie(AnimalSpecie animalSpecie) {
        this.animalSpecie = animalSpecie;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public String toString() {
        return "AdoptionModel{" +
                "idAdoption=" + idAdoption +
                ", adoptionDate=" + adoptionDate +
                ", idAnimal=" + idAnimal +
                ", animalSpecie=" + animalSpecie +
                ", idPerson=" + idPerson +
                ", idAdmin=" + idAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdoptionModel)) return false;
        AdoptionModel that = (AdoptionModel) o;
        return idAdoption.equals(that.idAdoption) &&
                adoptionDate.equals(that.adoptionDate) &&
                idAnimal.equals(that.idAnimal) &&
                animalSpecie == that.animalSpecie &&
                idPerson.equals(that.idPerson) &&
                idAdmin.equals(that.idAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdoption, adoptionDate, idAnimal, animalSpecie, idPerson, idAdmin);
    }
}
