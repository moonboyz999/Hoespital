package com.example.hoespital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainDashboardController {
    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> patientColumn;

    @FXML
    private TableColumn<Patient, String> idColumn;

    @FXML
    private TableColumn<Patient, String> genderColumn;

    @FXML
    private TableColumn<Patient, String> medicalHistoryColumn;

    @FXML
    private TableColumn<Patient, String> dateColumn;

    @FXML
    private TableColumn<Patient, String> treatmentColumn;

    @FXML
    private TableColumn<Patient, String> insuranceColumn;

    private static ObservableList<Patient> patientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the table columns with their respective data properties
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        medicalHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        treatmentColumn.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<>("insurance"));

        // Bind the ObservableList to the TableView
        patientTable.setItems(patientList);

        // Add some dummy data to the list
        addDummyData();
    }

    private void addDummyData() {
        // Example patient data to populate the table
        patientList.add(new Patient("John Doe", "0", "Male", "None", "2023-10-01", "Diabetes", "AAA Insurance"));
    }

    @FXML
    private void handleManageAppointmentsButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageappointments.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Manage Appointments");
            stage.setScene(scene);
            stage.setWidth(610);
            stage.setHeight(450);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        // Get the selected patient from the TableView
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editappointment.fxml"));
                AnchorPane root = fxmlLoader.load();

                // Get the controller instance and set the selected patient and current controller
                EditAppointmentController controller = fxmlLoader.getController();
                controller.setPatient(selectedPatient, this); // Ensure 'this' is an instance of MainDashboardController                

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
        } else {
            // Show an alert if no patient is selected
            showAlert("No Selection", "No Patient Selected", "Please select a patient in the table.");
        }
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        // Get the selected patient from the TableView
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete Patient");
            alert.setContentText("Are you sure you want to delete the selected patient?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected patient from the list
                patientList.remove(selectedPatient);
            }
        } else {
            // Show an alert if no patient is selected
            showAlert("No Selection", "No Patient Selected", "Please select a patient in the table.");
        }
    }

    private void showAlert(String title, String header, String content) {
        // Display an alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void addPatient(Patient patient) {
        // Add a new patient to the ObservableList
        patientList.add(patient);
    }

    @FXML
    private void handleAddNewPatientButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addappointment.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Add New Patient");
            stage.setScene(scene);
            stage.setWidth(610);
            stage.setHeight(450);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTableView() {
        // Refresh the TableView to reflect changes in the data
        patientTable.refresh(); // This updates the TableView with the latest data
    }
}
