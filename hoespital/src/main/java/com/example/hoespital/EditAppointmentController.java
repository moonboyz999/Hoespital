package com.example.hoespital;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAppointmentController {
    @FXML
    private TextField patientNameField;
    @FXML
    private TextField patientIdField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField medicalHistoryField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField treatmentField;
    @FXML
    private TextField insuranceField;

    private Patient selectedPatient;

    public void setPatient(Patient patient) {
        this.selectedPatient = patient;
        patientNameField.setText(patient.getName());
        patientIdField.setText(patient.getId());
        genderField.setText(patient.getGender());
        medicalHistoryField.setText(patient.getMedicalHistory());
        dateField.setText(patient.getDate());
        treatmentField.setText(patient.getTreatment());
        insuranceField.setText(patient.getInsurance());
    }

    @FXML
    private void handleSaveButtonAction() {
        selectedPatient.setName(patientNameField.getText());
        selectedPatient.setId(patientIdField.getText());
        selectedPatient.setGender(genderField.getText());
        selectedPatient.setMedicalHistory(medicalHistoryField.getText());
        selectedPatient.setDate(dateField.getText());
        selectedPatient.setTreatment(treatmentField.getText());
        selectedPatient.setInsurance(insuranceField.getText());

        // Close the current window
        Stage stage = (Stage) patientNameField.getScene().getWindow();
        stage.close();

        // Refresh the table view in the MainDashboardController
        MainDashboardController.refreshTableView();
    }

    @FXML
    private void handleCancelButtonAction() {
        // Close the current window
        Stage stage = (Stage) patientNameField.getScene().getWindow();
        stage.close();
    }
}