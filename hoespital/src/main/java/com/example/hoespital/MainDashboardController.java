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
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        medicalHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        treatmentColumn.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<>("insurance"));

        patientTable.setItems(patientList);

        // Add dummy data
        addDummyData();
    }

    private void addDummyData() {
        patientList.add(new Patient("John Doe", "0", "Male", "None", "2023-10-01", "Treatment A", "Yes"));
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
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editappointment.fxml"));
                AnchorPane root = fxmlLoader.load();
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
            showAlert("No Selection", "No Patient Selected", "Please select a patient in the table.");
        }
    }
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete Patient");
            alert.setContentText("Are you sure you want to delete the selected patient?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                patientList.remove(selectedPatient);
            }
        } else {
            showAlert("No Selection", "No Patient Selected", "Please select a patient in the table.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

  public static void addPatient(Patient patient) {
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
    public static void refreshTableView() {
        // Refresh the table view
        patientList.forEach(patient -> {
            patient.setName(patient.getName());
            patient.setId(patient.getId());
            patient.setGender(patient.getGender());
            patient.setMedicalHistory(patient.getMedicalHistory());
            patient.setDate(patient.getDate());
            patient.setTreatment(patient.getTreatment());
            patient.setInsurance(patient.getInsurance());
        });
    }
}