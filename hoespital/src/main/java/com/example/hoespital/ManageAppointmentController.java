package com.example.hoespital;
import com.example.hoespital.Patient;
import com.example.hoespital.EditAppointmentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
public class ManageAppointmentController {
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


    private static int lastPatientId = 0;

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        // Increment the patient ID
        lastPatientId++;
        String id = String.valueOf(lastPatientId);

        // Save the entered data
        String name = patientNameField.getText();
        String gender = genderField.getText();
        String medicalHistory = medicalHistoryField.getText();
        String date = dateField.getText();

        Patient newPatient = new Patient(name, id, gender, medicalHistory, date , "Treatment A", "Yes");    

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

        // Open the main dashboard window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maindashboard.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setTitle("Main Dashboard");
            newStage.setScene(scene);
            newStage.setWidth(610);
            newStage.setHeight(450);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //edit functionality 
    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hoespital/editappointment.fxml"));
            AnchorPane root = fxmlLoader.load();

            // Get the controller and set the selected patient
            EditAppointmentController controller = fxmlLoader.getController();
            controller.setPatient(new Patient(
                patientNameField.getText(),
                patientIdField.getText(),
                genderField.getText(),
                medicalHistoryField.getText(),
                dateField.getText(),
                treatmentField.getText(),
                insuranceField.getText()
            ));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Edit Patient");
            stage.setScene(scene);
            stage.setWidth(610);
            stage.setHeight(450);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}