package com.example.movietheaterbookingclassprojectlc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.movietheaterbookingclassprojectlc.DBUtils.changeScene;

public class AvailableMoviesController {

    @FXML
    private Button AddMovieButton;

    @FXML
    private Button CustomersButton;

    @FXML
    private Button DashBoardButton;

    @FXML
    private Button EditScreeningButton;

    @FXML
    private Button SignOutButton;

    @FXML
    private Label UsernameLBL;

    @FXML
    private Button availableMoviesButton;


    @FXML
    public void initialize()  {
        UsernameLBL.setText(DBUtils.user.getUsername());
        DashBoardButton.setOnAction(this::handleDashBoardButtonAction);
        AddMovieButton.setOnAction(this::handleAddMovieButtonAction);
        CustomersButton.setOnAction(this::handleCustomersButtonAction);
        EditScreeningButton.setOnAction(this::handleEditScreeningButtonAction);
        SignOutButton.setOnAction(this::handleSignOutButtonAction);
        availableMoviesButton.setOnAction(this::handleAvailableMoviesButtonAction);

    }

    private void handleAvailableMoviesButtonAction(ActionEvent actionEvent){
        try{
            changeScene("AvailableMovies.fxml", "Available Movies");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void handleDashBoardButtonAction(ActionEvent actionEvent){
        try {
            changeScene("Homepage.fxml", "Homepage");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleSignOutButtonAction(ActionEvent actionEvent){
        try {
            changeScene("Login.fxml", "Login");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleEditScreeningButtonAction(ActionEvent actionEvent){
        try {
            changeScene("EditScreening.fxml", "Edit Screening");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleCustomersButtonAction(ActionEvent actionEvent) {
        try {
            changeScene("Customers.fxml", "Customers");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAddMovieButtonAction(ActionEvent actionEvent) {
        try {
            changeScene("AddMovie.fxml", "Add Movie");
            CloseAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void CloseAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}
