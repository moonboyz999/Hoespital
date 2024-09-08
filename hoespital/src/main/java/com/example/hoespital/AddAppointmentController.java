package com.example.hoespital;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAppointmentController {
    @FXML
    private TextField patientNameField;

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

    private static int lastPatientId = 0;

    @FXML
    public void initialize() {
        genderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female"));
        treatmentChoiceBox.setItems(FXCollections.observableArrayList("diabetes", "lungs (Pulmonology)", "heart (Cardiology)", "kidneys (Nephrology)", "liver (Hepatology)", "stomach (Gastroenterology)", "brain (Neurology)", "bones (Orthopedics)", "skin (Dermatology)", "eyes (Ophthalmology)", "ears (Otolaryngology)", "teeth (Dentistry)", "mental health (Psychiatry)", "pregnancy (Obstetrics)", "children (Pediatrics)", "elderly (Geriatrics)", "Allergy (Asthma)"));
        insuranceChoiceBox.setItems(FXCollections.observableArrayList("AAA Insurance", "BBB Insurance", "No Insurance"));
    }

    @FXML
    private void handleAddAppointmentButtonAction(ActionEvent event) {
        String name = patientNameField.getText();
        String gender = genderChoiceBox.getValue();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;
        String treatment = treatmentChoiceBox.getValue();
        String insurance = insuranceChoiceBox.getValue();
        String medicalHistory = medicalHistoryField.getText();

        if (name.isEmpty() || gender == null || date == null || treatment == null || medicalHistory.isEmpty()) {
            showAlert("Error", "Missing Information", "Please fill out all fields.");
            return;
        }

        // Increment the patient ID
        lastPatientId++;
        String id = "A" + String.format("%03d", lastPatientId);

        // Declare and initialize the insurance variable
        
        Patient newPatient = new Patient(name, id, gender, medicalHistory, date, treatment, insurance);
        MainDashboardController.addPatient(newPatient);

        // Close the current window
        Stage stage = (Stage) patientNameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
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