package com.project.model;

import com.project.validators.OnlyLetters;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class MedicalModel {

    private Integer idMedical;

    @NotNull(message = "Please set the date when the chart was created.")
    private LocalDate chartDate;

    @OnlyLetters
    @NotNull(message = "Please set the disease.")
    private String disease;

    @NotNull(message = "Please set the medication.")
    private Medication medication;

    @NotNull
    private Integer idAnimal;

    @NotNull
    private Integer idVet;

    public MedicalModel(){

    }

    public MedicalModel(Integer idMedical, @NotNull(message = "Please set the date when the chart was created.") LocalDate chartDate, @NotNull(message = "Please set the disease.") String disease, @NotNull(message = "Please set the medication.") Medication medication, @NotNull Integer idAnimal, @NotNull Integer idVet) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.medication = medication;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
    }

    public MedicalModel(@NotNull(message = "Please set the date when the chart was created.") LocalDate chartDate, @NotNull(message = "Please set the disease.") String disease, @NotNull(message = "Please set the medication.") Medication medication, @NotNull Integer idAnimal, @NotNull Integer idVet) {
        this.chartDate = chartDate;
        this.disease = disease;
        this.medication = medication;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
    }

    public Integer getIdMedical() {
        return idMedical;
    }

    public void setIdMedical(Integer idMedical) {
        this.idMedical = idMedical;
    }

    public LocalDate getChartDate() {
        return chartDate;
    }

    public void setChartDate(LocalDate chartDate) {
        this.chartDate = chartDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Integer getIdVet() {
        return idVet;
    }

    public void setIdVet(Integer idVet) {
        this.idVet = idVet;
    }

    @Override
    public String toString() {
        return "MedicalModel{" +
                "idMedical=" + idMedical +
                ", chartDate=" + chartDate +
                ", disease='" + disease + '\'' +
                ", medication=" + medication +
                ", idAnimal=" + idAnimal +
                ", idVet=" + idVet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalModel)) return false;
        MedicalModel that = (MedicalModel) o;
        return idMedical.equals(that.idMedical) &&
                chartDate.equals(that.chartDate) &&
                disease.equals(that.disease) &&
                medication == that.medication &&
                idAnimal.equals(that.idAnimal) &&
                idVet.equals(that.idVet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMedical, chartDate, disease, medication, idAnimal, idVet);
    }
}
