package com.example.movietheaterbookingclassprojectlc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.movietheaterbookingclassprojectlc.DBUtils.changeScene;

public class EditScreeningController {
    @FXML
    private Button AddMovieButton;

    @FXML
    private Button CustomersButton;

    @FXML
    private Button DashBoardButton;

    @FXML
    private TextField DateTF;

    @FXML
    private TableColumn<Movies, String> DurationCol;

    @FXML
    private Button EditScreeningButton;

    @FXML
    private TableColumn<Movies, String> GenreCol;

    @FXML
    private TextField GenreTF;

    @FXML
    private TableColumn<Movies, String> MovieTitleCol;

    @FXML
    private Label MovieTitleLabel;

    @FXML
    private TextField MovieTitleTF;

    @FXML
    private TableColumn<Movies, String> ScreeningCol;

    @FXML
    private TableView<Movies> ScreeningTable;

    @FXML
    private Button SignOutButton;

    @FXML
    private Label UsernameLBL;

    @FXML
    private Button availableMoviesButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Label MovieDurationLBL;
    @FXML
    private ComboBox<String> isScreeningChoice;



    public ObservableList<Movies> movieList = FXCollections.observableArrayList();
    public ObservableList<String> options = FXCollections.observableArrayList("Yes", "No");


    @FXML
    public void initialize()  {
        UsernameLBL.setText(DBUtils.user.getUsername());
        DashBoardButton.setOnAction(this::handleDashBoardButtonAction);
        AddMovieButton.setOnAction(this::handleAddMovieButtonAction);
        CustomersButton.setOnAction(this::handleCustomersButtonAction);
        EditScreeningButton.setOnAction(this::handleEditScreeningButtonAction);
        SignOutButton.setOnAction(this::handleSignOutButtonAction);
        availableMoviesButton.setOnAction(this::handleAvailableMoviesButtonAction);

        MovieTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        GenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ScreeningCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        //populateTable();



        showMovieDetails(ScreeningTable.getSelectionModel().getSelectedItem());
        ScreeningTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMovieDetails(newValue));
        Movies movie = ScreeningTable.getSelectionModel().getSelectedItem();

        if(movie != null) {
            showMovieDetails(movie);
        }

        ScreeningTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) showMovieDetails(newValue);
        });


        if (isScreeningChoice != null) {
            isScreeningChoice.setItems(options);
        } else {
            System.out.println("BOZO BAD AT CODING FIX THIS NOW");
        }

        loadMoviesFromDatabase();
    }






    private void showMovieDetails(Movies movie) {
        if(movie != null) {
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


            imageView.setImage(new Image(movie.getImagePath()));

            ScreeningTable.setItems(movieList);
            ScreeningTable.refresh();
        }

        else{
            System.out.println("Movie is null");
        }
    }

    private void loadMoviesFromDatabase() {
        movieList.clear();
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

                movieList.add(movie);
            }
            ScreeningTable.setItems(movieList);

            ScreeningTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setMovieDetails(String title, String genre, String duration, LocalDate releaseDate, String image) {
        Movies movie = new Movies(0, title, genre, releaseDate.toString(), duration, image);
        MovieTitleTF.setText(title);
        GenreTF.setText(genre);
        MovieTitleLabel.setText(title);
        MovieDurationLBL.setText(duration);
        DateTF.setText(String.valueOf(releaseDate));




        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate.toString());
        movie.setTitle(title);
        movie.setGenre(genre);



        if (image != null) {
            File file = new File(image);
            Image img = new Image(file.toURI().toString());
            imageView.setImage(img);
            ScreeningTable.setItems(movieList);
            ScreeningTable.refresh();
        } else {
            ScreeningTable.setItems(movieList);
            ScreeningTable.refresh();
        }

        if(isScreeningChoice != null) {
            isScreeningChoice.setItems(options);


            if (isScreeningChoice.getValue().equals("Yes")) {
                ScreeningCol.setText("Yes");
            } else {
                ScreeningCol.setText("No");
            }
        }
        else{
            ScreeningCol.setText("No");
        }
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
