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

    private static ObservableList<Patient> patientList = FXCollections.observableArrayList();

    public static void addPatient(Patient patient) {
        patientList.add(patient);
    }

    @FXML
    public void initialize() {
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        medicalHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        patientTable.setItems(patientList);

        // Add dummy data
        addDummyData();
    }

    private void addDummyData() {
        patientList.add(new Patient("John Doe", "123", "Male", "None", "2023-10-01"));
        patientList.add(new Patient("Jane Smith", "456", "Female", "Asthma", "2023-10-02"));
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
}