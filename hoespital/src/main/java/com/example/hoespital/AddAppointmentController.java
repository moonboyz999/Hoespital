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
    private TextField treatmentField;

    @FXML
    private TextField medicalHistoryField;

    @FXML
    public void initialize() {
        genderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female"));
    }

    @FXML
    private void handleAddAppointmentButtonAction(ActionEvent event) {
        String name = patientNameField.getText();
        String gender = genderChoiceBox.getValue();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;
        String treatment = treatmentField.getText();
        String medicalHistory = medicalHistoryField.getText();

        if (name.isEmpty() || gender == null || date == null || treatment.isEmpty() || medicalHistory.isEmpty()) {
            showAlert("Error", "Missing Information", "Please fill out all fields.");
            return;
        }

        Patient newPatient = new Patient(name, "", gender, medicalHistory, date);
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