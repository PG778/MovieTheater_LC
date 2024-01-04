module com.example.movietheaterbookingclassprojectlc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.movietheaterbookingclassprojectlc to javafx.fxml;
    exports com.example.movietheaterbookingclassprojectlc;
}