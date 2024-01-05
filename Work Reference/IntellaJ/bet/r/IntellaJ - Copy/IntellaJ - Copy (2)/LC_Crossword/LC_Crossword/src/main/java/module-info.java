module com.example.lc_crossword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lc_crossword to javafx.fxml;
    exports com.example.lc_crossword;
}