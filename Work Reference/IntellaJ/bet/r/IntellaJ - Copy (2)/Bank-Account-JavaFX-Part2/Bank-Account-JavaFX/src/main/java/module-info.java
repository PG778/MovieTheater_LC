module com.example.bankaccountjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bankaccountjavafx to javafx.fxml;
    exports com.example.bankaccountjavafx;
}