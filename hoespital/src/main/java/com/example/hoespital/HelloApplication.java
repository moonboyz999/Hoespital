package com.example.hoespital;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//kai
//Talha
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setResizable(false);  // Disable resizing
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}