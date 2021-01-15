package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.Medication;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_chart")
public class MedicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medical_chart", insertable = false, updatable = false)
    private Integer idMedical;

    @Column(name = "chart_date")
    private LocalDate chartDate;

    @Column(name ="disease")
    private String disease;

    @Enumerated(EnumType.STRING)
    @Column(name = "medication")
    private Medication medication;


    @JsonIgnoreProperties("medicalEntity")
    @OneToOne(mappedBy = "medicalEntity", cascade = CascadeType.ALL, optional = false)
    private AnimalEntity animalEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vet")
    private VetEntity vetEntity;

    public MedicalEntity(){

    }

    public MedicalEntity(Integer idMedical, LocalDate chartDate, String disease, Medication medication, AnimalEntity animalEntity, VetEntity vetEntity) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.medication = medication;
        this.animalEntity = animalEntity;
        this.vetEntity = vetEntity;
    }

    public MedicalEntity(LocalDate chartDate, String disease, Medication medication, AnimalEntity animalEntity, VetEntity vetEntity) {
        this.chartDate = chartDate;
        this.disease = disease;
        this.medication = medication;
        this.animalEntity = animalEntity;
        this.vetEntity = vetEntity;
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

    public AnimalEntity getAnimalEntity() {
        return animalEntity;
    }

    public void setAnimalEntity(AnimalEntity animalEntity) {
        this.animalEntity = animalEntity;
    }

    public VetEntity getVetEntity() {
        return vetEntity;
    }

    public void setVetEntity(VetEntity vetEntity) {
        this.vetEntity = vetEntity;
    }
}
