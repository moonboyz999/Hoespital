package com.example.hoespital;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BillingController {
    @FXML
    private TextField patientIdField;

    @FXML
    private TextField daysField;

    @FXML
    private TextField billingAmountField;

    private static final Map<String, Double> TREATMENT_COSTS = new HashMap<>();
    private static final Map<String, String> PATIENT_TREATMENTS = new HashMap<>();
    private static final Map<String, Double> INSURANCE_DISCOUNTS = new HashMap<>(); // New map for insurance discounts

    static {
        TREATMENT_COSTS.put("Diabetes", 100.0);
        TREATMENT_COSTS.put("Lungs (Pulmonology)", 150.0);
        TREATMENT_COSTS.put("Heart (Cardiology)", 200.0);

        INSURANCE_DISCOUNTS.put("AAA Insurance", 0.25);
        INSURANCE_DISCOUNTS.put("BBB Insurance", 0.50);
        INSURANCE_DISCOUNTS.put("No Insurance", 0.0);
    }

    @FXML
    private void handleCalculateBillingButtonAction() {
        try {
            String patientId = patientIdField.getText();
            int days = Integer.parseInt(daysField.getText());

            String treatment = getTreatmentByPatientId(patientId);
            double costPerDay = TREATMENT_COSTS.getOrDefault(treatment, 100.0); // Default cost if treatment not found

            String insurance = getInsuranceByPatientId(patientId);
            double discount = INSURANCE_DISCOUNTS.getOrDefault(insurance, 0.0); // Default discount if insurance not found

            double discountedCostPerDay = costPerDay * (1 - discount);
            double totalBillingAmount = days * costPerDay * (1 - discount); // Apply discount
            billingAmountField.setText(String.format("%.2f", totalBillingAmount));

            // Debugging: Print the calculated amount
            System.out.println("Total Billing Amount: " + totalBillingAmount);

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for days.");
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

    private String getInsuranceByPatientId(String patientId) {
        // Fetch the insurance type from the map based on patient ID
        return PATIENT_TREATMENTS.getOrDefault(patientId, "No Insurance");
    }

    public static void setPatientTreatment(String patientId, String treatment) {
        PATIENT_TREATMENTS.put(patientId, treatment);
    }
}