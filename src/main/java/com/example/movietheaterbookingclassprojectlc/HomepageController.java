package com.example.movietheaterbookingclassprojectlc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.movietheaterbookingclassprojectlc.DBUtils.changeScene;

public class HomepageController {

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


    }
    public void handleAvailableMoviesButtonAction(){
        try{
            changeScene("AvailableMovies.fxml", "Available Movies");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void handleDashBoardButtonAction(){
        try {
            changeScene("Homepage.fxml", "Homepage");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleSignOutButtonAction(){
        try {
            changeScene("Login.fxml", "Login");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleEditScreeningButtonAction(){
        try {
            changeScene("EditScreening.fxml", "Edit Screening");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleCustomersButtonAction() {
        try {
            changeScene("Customers.fxml", "Customers");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddMovieButtonAction() {
        try {
            changeScene("AddMovie.fxml", "Add Movie");
            CloseAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void CloseAction() {
        Stage stage = (Stage) SignOutButton.getScene().getWindow();

        stage.close();
    }


}
