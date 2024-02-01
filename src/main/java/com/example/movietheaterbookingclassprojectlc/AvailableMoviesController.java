package com.example.movietheaterbookingclassprojectlc;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

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
    private TableView<Movies> movieTable;

    @FXML
    private TableColumn<Movies, String> MovieTitleColumn;

    @FXML
    private TableColumn<Movies, String> GenreColumn;

    @FXML
    private TableColumn<Movies, String> PublishedDateColumn;

    public static ObservableList<Movies> movieList;

    @FXML
    public void initialize()  {

        AddMovieButton.setOnAction(actionEvent -> handleAddMovieButtonAction());
        CustomersButton.setOnAction(actionEvent -> handleCustomersButtonAction());
        DashBoardButton.setOnAction(actionEvent -> handleDashBoardButtonAction());
        EditScreeningButton.setOnAction(actionEvent -> handleEditScreeningButtonAction());
        SignOutButton.setOnAction(actionEvent -> handleSignOutButtonAction());
        availableMoviesButton.setOnAction(actionEvent -> handleAvailableMoviesButtonAction());
        showMovieDetails(movieTable.getSelectionModel().getSelectedItem());
        movieTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMovieDetails(newValue));
        Movies movie = movieTable.getSelectionModel().getSelectedItem();



        movieTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) showMovieDetails(newValue);
        });




        loadMoviesIntoTable();




     //   showMovieDetails(movieTable.getSelectionModel().getSelectedItem());

    }



    public void loadMoviesIntoTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tickets", "root", "password");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Movies");

            while (rs.next()) {
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                Movies movie = new Movies(title, genre, duration);
                movieTable.getItems().add(movie);
                MovieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                GenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                PublishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));


                System.out.println("Title: " + title + ", Genre: " + genre + ", Duration: " + duration);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    public void handleAvailableMoviesButtonAction(){

            Movies movie = movieTable.getSelectionModel().getSelectedItem();
            if (movie != null) {
                String title = movie.getTitle();
                String genre = movie.getGenre();
                String duration = movie.getDuration();
                LocalDate releaseDate = LocalDate.parse(movie.getReleaseDate());
                String image = movie.getImagePath();

                setMovieDetails(title, genre, duration, releaseDate, image);
                System.out.println("Image path: " + movie.getImagePath());
                System.out.println(movie.getDuration());
                System.out.println(movie.getReleaseDate());
                System.out.printf("Title: %s, Genre: %s, Duration: %s, Release Date: %s, Image: %s\n", title, genre, duration, releaseDate, image);

            }
        }


    private void loadMoviesFromDatabase() {
        String TitleName;
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movies movie = new Movies(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("release_date"),
                        rs.getString("duration"),
                        rs.getString("image"));

                System.out.println("Duration: " + rs.getString("duration")); // Check if this prints correctly
                System.out.println("Release Date: " + rs.getString("release_date")); // Check if this prints correctly


            }
            movieTable.setItems(movieList);

            movieTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setMovieDetails(String title, String genre, String duration, LocalDate releaseDate, String image) {
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Duration: " + duration);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Image: " + image);
    }


    private void showMovieDetails (Movies movie){
        if (movie != null) {
            String title = movie.getTitle();
            String genre = movie.getGenre();
            String duration = movie.getDuration();
            String releaseDateString = movie.getReleaseDate();
            LocalDate releaseDate = null;

            //Checking if string is null or empty before parsing
            if(releaseDateString != null && !releaseDateString.isEmpty()) {
                releaseDate = LocalDate.parse(movie.getReleaseDate());
            }

            String image = movie.getImagePath();

            setMovieDetails(title, genre, duration, releaseDate, image);
            System.out.println("Image path: " + movie.getImagePath());
            System.out.println(movie.getDuration());
            System.out.println(movie.getReleaseDate());
            System.out.printf("Title: %s, Genre: %s, Duration: %s, Release Date: %s, Image: %s\n", title, genre, duration, releaseDate, image);

            MovieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));


            movieTable.setItems(movieList);
            movieTable.refresh();
        } else {
            System.out.println("Movie is null");
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
