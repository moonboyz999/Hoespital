package com.example.hoespital;

public class PatientRecord {
    private String patient;
    private String id;
    private String gender;
    private String medicalHistory;
    private String date;

    public PatientRecord(String patient, String id, String gender, String medicalHistory, String date) {
        this.patient = patient;
        this.id = id;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
        this.date = date;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}