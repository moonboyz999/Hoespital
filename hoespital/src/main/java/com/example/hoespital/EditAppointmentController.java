package com.example.hoespital;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;


public class EditAppointmentController {
    @FXML
    private TextField patientNameField;
    @FXML
    private TextField patientIdField;
    @FXML
    private ChoiceBox<String> genderChoiceBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> insuranceChoiceBox;
    @FXML
    private ChoiceBox<String> treatmentChoiceBox;
    @FXML
    private TextField medicalHistoryField;

    private Patient selectedPatient;

    @FXML
    public void initialize() {
        genderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female"));
        treatmentChoiceBox.setItems(FXCollections.observableArrayList(
            "diabetes", "lungs (Pulmonology)", "heart (Cardiology)", "kidneys (Nephrology)",
            "liver (Hepatology)", "stomach (Gastroenterology)", "brain (Neurology)", 
            "bones (Orthopedics)", "skin (Dermatology)", "eyes (Ophthalmology)", 
            "ears (Otolaryngology)", "teeth (Dentistry)", "mental health (Psychiatry)", 
            "pregnancy (Obstetrics)", "children (Pediatrics)", "elderly (Geriatrics)", 
            "Allergy (Asthma)"
        ));
        insuranceChoiceBox.setItems(FXCollections.observableArrayList("AAA Insurance", "BBB Insurance", "No Insurance"));
    }

    public void setPatient(Patient patient) {
        this.selectedPatient = patient;
        patientNameField.setText(patient.getName());
        patientIdField.setText(patient.getId());
        genderChoiceBox.setValue(patient.getGender());
        medicalHistoryField.setText(patient.getMedicalHistory());
        datePicker.setValue(LocalDate.parse(patient.getDate())); // Use datePicker here, not dateField
        treatmentChoiceBox.setValue(patient.getTreatment());
        insuranceChoiceBox.setValue(patient.getInsurance());
    }

    @FXML
    private void handleSaveChangesButtonAction() {
        if (selectedPatient != null) {
            String name = patientNameField.getText();
            String gender = genderChoiceBox.getValue();
            String date = datePicker.getValue() != null ? datePicker.getValue().toString() : null;
            String treatment = treatmentChoiceBox.getValue();
            String insurance = insuranceChoiceBox.getValue();
            String medicalHistory = medicalHistoryField.getText();

            if (name.isEmpty() || gender == null || date == null || treatment == null || medicalHistory.isEmpty()) {
                showAlert("Error", "Missing Information", "Please fill out all fields.");
                return;
            }

            selectedPatient.setName(name);
            selectedPatient.setId(patientIdField.getText());
            selectedPatient.setGender(gender);
            selectedPatient.setDate(date);
            selectedPatient.setTreatment(treatment);
            selectedPatient.setInsurance(insurance);
            selectedPatient.setMedicalHistory(medicalHistory);

            // Close the current window
            Stage stage = (Stage) patientNameField.getScene().getWindow();
            stage.close();

            // Refresh the table view in the MainDashboardController
            MainDashboardController.refreshTableView();
        } else {
            System.err.println("No patient selected to save.");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        // Close the current window
        Stage stage = (Stage) patientNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
