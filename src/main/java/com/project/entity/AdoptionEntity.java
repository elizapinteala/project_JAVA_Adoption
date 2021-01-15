package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.AdoptionModel;
import com.project.model.AnimalSpecie;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adoption")
public class AdoptionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adoption", insertable = false, updatable = false)
    private Integer idAdoption;

    @Column(name = "adoption_date")
    private LocalDate adoptionDate;

    @JsonIgnoreProperties("adoptionEntity")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal")
    private AnimalEntity idAnimal;


    @Enumerated(EnumType.STRING)
    @Column(name="specie")
    private AnimalSpecie animalSpecie;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private PersonEntity idPerson;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_admin")
    private AdminEntity idAdmin;

    public AdoptionEntity(){}

    public AdoptionEntity(AdoptionModel adoptionModel){
        this.idAdoption=adoptionModel.getIdAdoption();
        this.adoptionDate=adoptionModel.getAdoptionDate();
        this.animalSpecie=adoptionModel.getAnimalSpecie();

    }

    public AdoptionEntity(Integer idAdoption, LocalDate adoptionDate, AnimalEntity idAnimal,AnimalSpecie animalSpecie, PersonEntity idPerson, AdminEntity idAdmin) {
        this.idAdoption = idAdoption;
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

    public AnimalEntity getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(AnimalEntity idAnimal) {
        this.idAnimal = idAnimal;
    }

    public AnimalSpecie getAnimalSpecie() {
        return animalSpecie;
    }

    public void setAnimalSpecie(AnimalSpecie animalSpecie) {
        this.animalSpecie = animalSpecie;
    }

    public PersonEntity getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(PersonEntity idPerson) {
        this.idPerson = idPerson;
    }

    public AdminEntity getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(AdminEntity idAdmin) {
        this.idAdmin = idAdmin;
    }
}
