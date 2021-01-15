package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.model.PersonModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", insertable = false, updatable = false)
    private Integer idPerson;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnoreProperties("idPerson")
    @OneToMany(mappedBy = "idPerson")
    private List<AdoptionEntity> adoptions;

    public PersonEntity(){}

    public PersonEntity(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public PersonEntity(Integer idPerson, String firstName, String lastName, String phone, String email) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

    }

    public  PersonEntity(PersonModel personModel){
        this.idPerson= personModel.getIdPerson();
        this.firstName=personModel.getFirstName();
        this.lastName=personModel.getLastName();
        this.phone=personModel.getPhone();
        this.email=personModel.getEmail();


    }


    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
