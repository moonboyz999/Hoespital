package com.example.hoespital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text actiontarget;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        if (isValidCredentials(usernameField.getText(), passwordField.getText())) {
            proceedToMainDashboard();
        } else {
            actiontarget.setText("Invalid username or password!");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace with actual credential validation logic
        return "admin".equals(username) && "admin".equals(password);
    }

    private void proceedToMainDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maindashboard.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Main Dashboard");
            stage.setScene(scene);
            stage.setWidth(610);
            stage.setHeight(450);
            stage.setResizable(false);
            stage.show();
    
            // Close the current login window
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}