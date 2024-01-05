module com.example.blanket {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blanket to javafx.fxml;
    exports com.example.blanket;
}