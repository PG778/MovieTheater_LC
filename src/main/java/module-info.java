module com.example.movietheaterbookingclassprojectlc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.movietheaterbookingclassprojectlc to javafx.fxml;
    exports com.example.movietheaterbookingclassprojectlc;
}