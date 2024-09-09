package com.example.hoespital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAppointmentController {

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
    private void handleEditButtonAction(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hoespital/editappointment.fxml"));
                AnchorPane root = fxmlLoader.load();

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
            showAlert("No Selection", "No Patient Selected", "Please select a patient in the table.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}