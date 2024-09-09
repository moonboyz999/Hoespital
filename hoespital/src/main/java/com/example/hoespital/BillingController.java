package com.example.hoespital;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BillingController {

    @FXML
    private TextField patientIdField;

    @FXML
    private TextField daysField;

    @FXML
    private TextField billingAmountField;

    private static final Map<String, Double> TREATMENT_COSTS = new HashMap<>();
    private static final Map<String, String> PATIENT_TREATMENTS = new HashMap<>();

    static {
        TREATMENT_COSTS.put("Diabetes", 100.0);
        TREATMENT_COSTS.put("Lungs (Pulmonology)", 150.0);
        TREATMENT_COSTS.put("Heart (Cardiology)", 200.0);
        TREATMENT_COSTS.put("Kidneys (Nephrology)", 180.0);
        TREATMENT_COSTS.put("Liver (Hepatology)", 170.0);
        TREATMENT_COSTS.put("Stomach (Gastroenterology)", 160.0);
        TREATMENT_COSTS.put("Brain (Neurology)", 220.0);
        TREATMENT_COSTS.put("Bones (Orthopedics)", 140.0);
        TREATMENT_COSTS.put("Skin (Dermatology)", 130.0);
        TREATMENT_COSTS.put("Eyes (Ophthalmology)", 120.0);
        TREATMENT_COSTS.put("Ears (Otolaryngology)", 110.0);
        TREATMENT_COSTS.put("Teeth (Dentistry)", 90.0);
        TREATMENT_COSTS.put("Mental Health (Psychiatry)", 200.0);
        TREATMENT_COSTS.put("Pregnancy (Obstetrics)", 210.0);
        TREATMENT_COSTS.put("Children (Pediatrics)", 160.0);
        TREATMENT_COSTS.put("Elderly (Geriatrics)", 170.0);
        TREATMENT_COSTS.put("Allergy (Asthma)", 150.0);
    }

    @FXML
    private void handleCalculateBillingButtonAction() {
        try {
            String patientId = patientIdField.getText();
            int days = Integer.parseInt(daysField.getText());

            String treatment = getTreatmentByPatientId(patientId);
            double costPerDay = TREATMENT_COSTS.getOrDefault(treatment, 100.0); // Default cost if treatment not found

            // Debugging statements
            System.out.println("Patient ID: " + patientId);
            System.out.println("Treatment: " + treatment);
            System.out.println("Cost per Day: " + costPerDay);
            System.out.println("Number of Days: " + days);

            double totalBillingAmount = days * costPerDay;
            System.out.println("Total Billing Amount: " + totalBillingAmount); // Debugging statement

            billingAmountField.setText(String.format("%.2f", totalBillingAmount));
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number of days.");
        }
    }

    @FXML
    private void handleBackToDashboardButtonAction() {
        // Close the billing window and return to the dashboard
        Stage stage = (Stage) patientIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String getTreatmentByPatientId(String patientId) {
        // Fetch the treatment type from the map based on patient ID
        return PATIENT_TREATMENTS.getOrDefault(patientId, "Unknown");
    }

    public static void setPatientTreatment(String patientId, String treatment) {
        PATIENT_TREATMENTS.put(patientId, treatment);
    }
}