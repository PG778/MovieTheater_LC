module com.example.battleship_lc_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.battleship_lc_fx to javafx.fxml;
    exports com.example.battleship_lc_fx;
}