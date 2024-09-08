package com.example.hoespital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainDashboardController {

    @FXML
    private TableView<PatientRecord> patientTable;

    @FXML
    private TableColumn<PatientRecord, String> patientColumn;

    @FXML
    private TableColumn<PatientRecord, String> idColumn;

    @FXML
    private TableColumn<PatientRecord, String> genderColumn;

    @FXML
    private TableColumn<PatientRecord, String> medicalHistoryColumn;

    @FXML
    private TableColumn<PatientRecord, String> dateColumn;

    @FXML
    public void initialize() {
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        medicalHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Populate the table with dummy records
        patientTable.setItems(getPatientRecords());
    }

    private ObservableList<PatientRecord> getPatientRecords() {
        ObservableList<PatientRecord> records = FXCollections.observableArrayList();
        records.add(new PatientRecord("John Doe", "P123", "Male", "None", "2023-10-01"));
        records.add(new PatientRecord("Jane Doe", "P124", "Female", "Asthma", "2023-10-02"));
        records.add(new PatientRecord("Sam Smith", "P125", "Male", "Diabetes", "2023-10-03"));
        records.add(new PatientRecord("Alice Brown", "P126", "Female", "Hypertension", "2023-10-04"));
        records.add(new PatientRecord("Bob White", "P127", "Male", "Cardiac", "2023-10-05"));
        return records;
    }

    @FXML
    private void handleManageAppointmentsButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageappointment.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Manage Appointments");
            stage.setScene(scene);
            stage.setWidth(600);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.show();

            // Close the current dashboard window if needed
            Stage currentStage = (Stage) patientTable.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}