package com.example.movietheaterbookingclassprojectlc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class HelloController {

    Random random = new Random();
    AnchorPane root = new AnchorPane();




    @FXML
    private Button SigninButton;

    @FXML
    private Label label;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signup_button;

    @FXML
    private TextField username_field;

    @FXML
    public void initialize() {
        SigninButton.setOnAction(this::handleLoginButtonAction);
        signup_button.setOnAction(this::handleSignupButtonAction);
    }

    private void handleLoginButtonAction(ActionEvent actionEvent) {
        String username = username_field.getText();
        String password = password_field.getText();

        try {
            DBUtils.loginUser(username, password, "Homepage.fxml", "Homepage");
            CloseAction(actionEvent);
        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to login: " + e.getMessage());
            alert.show();
        }
    }

    private void handleSignupButtonAction(ActionEvent actionEvent) {
        String username = username_field.getText();
        String password = password_field.getText();

        try {
            DBUtils.signUpUser(username, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully registered!");
            alert.show();
            handleLoginButtonAction(actionEvent);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to register: " + e.getMessage());
            alert.show();
        }
    }
    @FXML
    private void CloseAction(ActionEvent event) {
        Stage stage = (Stage) SigninButton.getScene().getWindow();
        stage.close();
    }

}
