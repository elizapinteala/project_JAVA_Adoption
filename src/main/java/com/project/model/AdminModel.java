package com.project.model;

import com.project.validators.OnlyLetters;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class AdminModel {


    private Integer idAdmin;

    @OnlyLetters
    @NotNull(message = "Please set the first name of the admin.")
    private String firstName;

    @OnlyLetters
    @NotNull(message = "Please set the last name of the admin.")
    private String lastName;

    @Email(message = "Email should be valid.")
    @NotNull(message = "Please set the email of the admin.")
    private String email;

    private List<AdoptionModel> adoptionModelList;

    public AdminModel(){}

    public AdminModel(@NotNull(message = "Please set the first name of the admin.") String firstName, @NotNull(message = "Please set the last name of the admin.") String lastName, @Email(message = "Email should be valid.") @NotNull(message = "Please set the email of the admin.") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public AdminModel(Integer idAdmin, @NotNull(message = "Please set the first name of the admin.") String firstName, @NotNull(message = "Please set the last name of the admin.") String lastName, @Email(message = "Email should be valid.") @NotNull(message = "Please set the email of the admin.") String email) {
        this.idAdmin = idAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public List<AdoptionModel> getAdoptionModelList() {
        return adoptionModelList;
    }

    public void setAdoptionModelList(List<AdoptionModel> adoptionModelList) {
        this.adoptionModelList = adoptionModelList;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "idAdmin=" + idAdmin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminModel)) return false;
        AdminModel that = (AdminModel) o;
        return idAdmin.equals(that.idAdmin) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                email.equals(that.email) &&
                Objects.equals(adoptionModelList, that.adoptionModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdmin, firstName, lastName, email, adoptionModelList);
    }
}
