module com.example.factoryworkers_lc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.factoryworkers_lc to javafx.fxml;
    exports com.example.factoryworkers_lc;
}