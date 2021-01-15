package com.project.model;

import com.project.validators.OnlyDigits;
import com.project.validators.OnlyLetters;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class VetModel {


    private Integer idVet;

    @OnlyDigits
    @Size(min = 6, max = 6, message = "The size should be 6.")
    @NotNull(message = "Please set the number of the license.")
    private String license;

    @OnlyLetters
    @NotNull(message = "Please set the first name of the vet.")
    private String firstName;

    @OnlyLetters
    @NotNull(message = "Please set the last name of the vet.")
    private String lastName;

    @Email(message = "Email should be valid.")
    @NotNull(message =  "Please set the email of the vet.")
    private String email;

    private List<MedicalModel> medicalModelList;


    public VetModel(){}

    public VetModel(@Size(min = 6, max = 6, message = "The size should be 6.") @NotNull(message = "Please set the number of the license.") String license, @NotNull(message = "Please set the first name of the vet.") String firstName, @NotNull(message = "Please set the last name of the vet.") String lastName, @Email(message = "Email should be valid.") @NotNull(message = "Please set the email of the vet.") String email) {
        this.license = license;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public VetModel(Integer idVet, @Size(min = 6, max = 6, message = "The size should be 6.") @NotNull(message = "Please set the number of the license.") String license, @NotNull(message = "Please set the first name of the vet.") String firstName, @NotNull(message = "Please set the last name of the vet.") String lastName, @Email(message = "Email should be valid.") @NotNull(message = "Please set the email of the vet.") String email) {
        this.idVet = idVet;
        this.license = license;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public List<MedicalModel> getMedicalModelList() {
        return medicalModelList;
    }

    @Override
    public String toString() {
        return "VetModel{" +
                "idVet=" + idVet +
                ", license='" + license + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", medicalModelList=" + medicalModelList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VetModel)) return false;
        VetModel vetModel = (VetModel) o;
        return idVet.equals(vetModel.idVet) &&
                license.equals(vetModel.license) &&
                firstName.equals(vetModel.firstName) &&
                lastName.equals(vetModel.lastName) &&
                email.equals(vetModel.email) &&
                Objects.equals(medicalModelList, vetModel.medicalModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVet, license, firstName, lastName, email, medicalModelList);
    }
}
