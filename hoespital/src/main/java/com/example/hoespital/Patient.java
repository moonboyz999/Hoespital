package com.example.hoespital;

public class Patient {
    private String name;
    private String id;
    private String gender;
    private String medicalHistory;
    private String date;

    public Patient(String name, String id, String gender, String medicalHistory, String date) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getDate() {
        return date;
    }
}