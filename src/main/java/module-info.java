module com.example.assigmentsecu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assigmentsecu to javafx.fxml;
    exports com.example.assigmentsecu;
}