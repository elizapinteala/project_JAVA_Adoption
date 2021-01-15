package com.project.model;
import com.project.validators.OnlyDigits;
import com.project.validators.OnlyLetters;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


public class PersonModel {


    private Integer idPerson;

    @OnlyLetters
    @NotNull(message = "Please set the first name.")
    private String firstName;

    @OnlyLetters
    @NotNull(message = "Please set the last name.")
    private String lastName;

    @Size(min = 10, max = 10, message = "The size should be 10.")
    @NotNull(message = "Please set the phone number.")
    @OnlyDigits
    private String phone;


    @Email(message = "Email should be valid.")
    @NotNull(message = "Please set the email.")
    private String email;

    private List<AdoptionModel> adoptionModelList;

    public PersonModel(){}

    public PersonModel(@NotNull(message = "Please set the first name.") String firstName, @NotNull(message = "Please set the last name.") String lastName, @Size(min = 10, max = 10, message = "The size should be 10.") @NotNull(message = "Please set the phone number.") String phone, @Email(message = "Email should be valid.") @NotNull(message = "Please set the email.") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public PersonModel(Integer idPerson, @NotNull String firstName, @NotNull String lastName, @Size(min = 10, max = 10, message = "The size should be 10.") @NotNull String phone, @Email(message = "Email should be valid.") @NotNull String email) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
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

    public List<AdoptionModel> getAdoptionModelList() {
        return adoptionModelList;
    }

    public void setAdoptionModelList(List<AdoptionModel> adoptionModelList) {
        this.adoptionModelList = adoptionModelList;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "idPerson=" + idPerson +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adoptionModelList=" + adoptionModelList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonModel)) return false;
        PersonModel that = (PersonModel) o;
        return idPerson.equals(that.idPerson) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                phone.equals(that.phone) &&
                email.equals(that.email) &&
                Objects.equals(adoptionModelList, that.adoptionModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, firstName, lastName, phone, email, adoptionModelList);
    }
}
