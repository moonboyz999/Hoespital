module com.example.hoespital {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.hoespital to javafx.fxml;
    exports com.example.hoespital;
}