package com.example.movietheaterbookingclassprojectlc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static com.example.movietheaterbookingclassprojectlc.DBUtils.changeScene;

public class AddMovieController {

    @FXML
    private TextField movieSearchTextField;

    @FXML
    private Button AddMovieButton;

    @FXML
    private Button CustomersButton;

    @FXML
    private Button DashBoardButton;

    @FXML
    private TableColumn<Movies, String> DurationColumn;

    @FXML
    private Button EditScreeningButton;

    @FXML
    private TableColumn<Movies, String> GenreColumn;

    @FXML
    private ImageView ImageView;

    @FXML
    private TableView<Movies> MovieTable;

    @FXML
    private Button btn_Delete;  // to implement delete feature

    @FXML
    private Button btn_Update;  // to implement update feature

    @FXML
    private TableColumn<Movies, String> MovieTitleColumn;

    @FXML
    private DatePicker PublishDate;

    @FXML
    private TableColumn<Movies, String> PublishedDateColumn;

    @FXML
    private Button SignOutButton;

    @FXML
    private Label UsernameLBL;

    @FXML
    private Button availableMoviesButton;

    @FXML
    private Button btn_Import;

    @FXML
    private Button btn_Insert;

    @FXML
    private TextField lbl_Duration;

    @FXML
    private TextField lbl_genre;

    @FXML
    private TextField lbl_title;

    private ObservableList<Movies> movieList;

    @FXML
    public void initialize()  {


        UsernameLBL.setText(DBUtils.user.getUsername());

        movieList = FXCollections.observableArrayList();


        ColumnFill();
        loadMoviesFromDatabase();

        MovieTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMovieDetails(newValue));




    }

    public void ColumnFill() {

       try{
        MovieTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        GenreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        DurationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        PublishedDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        MovieTable.setItems(movieList);
        } catch (Exception e) {
            e.printStackTrace();
       }

        Movies movie = new Movies(0, null, null, null, null, null);
        System.out.println(movie.getDuration());
        System.out.println(movie.getReleaseDate());
        System.out.println(movie.getTitle());
        System.out.println(movie.getGenre());


    }


    private void loadMoviesFromDatabase() {
        int id = 0;
        movieList.clear();
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Movies movie = new Movies(id, null, null, null, null, null);
                String publishDate = (PublishDate.getValue() == null) ? "" : PublishDate.getValue().toString();
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getString("duration"));
                movie.setReleaseDate(rs.getString("release_date"));
                movie.setImagePath(rs.getString("image"));


                movieList.add(movie);


            }
            stmt.execute();
            MovieTable.setItems(movieList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showMovieDetails(Movies movie) {
        String title = movie != null ? movie.getTitle() : "";
        String genre = movie != null ? movie.getGenre() : "";
        String duration = movie != null ? movie.getDuration() : "";
        LocalDate releaseDate = movie != null ? LocalDate.parse(movie.getReleaseDate()) : null;
        String image = movie != null ? movie.getImagePath() : null;

        setMovieDetails(title, genre, duration, releaseDate, image, movie);
        System.out.println("Image path: " + movie.getImagePath());
        System.out.println(movie.getDuration());
        System.out.println(movie.getReleaseDate());
        System.out.printf("Title: %s, Genre: %s, Duration: %s, Release Date: %s, Image: %s\n", title, genre, duration, releaseDate, image);


        ImageView.setImage(new Image(movie.getImagePath()));


    }
    private void setMovieDetails(String title, String genre, String duration, LocalDate releaseDate, String image, Movies movie) {
        lbl_title.setText(title);
        lbl_genre.setText(genre);
        lbl_Duration.setText(duration);
        PublishDate.setValue(releaseDate);

        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate.toString());
        movie.setTitle(title);
        movie.setGenre(genre);

        String imagePath = image != null ? movie.getImagePath() : null;

        if (image != null) {
            File file = new File(image);
            Image img = new Image(file.toURI().toString());
            ImageView.setImage(img);
        } else {
            ImageView.setImage(null);
        }
    }





    @FXML
    private void handleDeleteMovie() {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movies WHERE id = ?;");
            stmt.setInt(1, MovieTable.getSelectionModel().getSelectedItem().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadMoviesFromDatabase();
    }

    @FXML
    private void handleUpdateMovie() {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE movies SET title = ?, genre = ?, duration = ?, release_date = ?, image = ? WHERE id = ?;");
            stmt.setString(1, lbl_title.getText());
            stmt.setString(2, lbl_genre.getText());
            stmt.setInt(3, Integer.parseInt(lbl_Duration.getText()));
            stmt.setDate(4, Date.valueOf(PublishDate.getValue()));
            stmt.setString(5, MovieTable.getSelectionModel().getSelectedItem().getImagePath());
            stmt.setInt(6, MovieTable.getSelectionModel().getSelectedItem().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadMoviesFromDatabase();
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

    public void handleImportButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        Movies movie = MovieTable.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ImageView.setImage(image);
            movie.setImagePath(selectedFile.getAbsolutePath());
        } else {
            return;
        }
    }

    public void handleInsertButtonAction() {
        String title = lbl_title.getText();
        String genre = lbl_genre.getText();
        String duration = lbl_Duration.getText();
        LocalDate releaseDate = PublishDate.getValue();


        if (!duration.matches("\\d+")) {
            System.out.println("Invalid duration. Please enter a numeric value.");
            return;
        }

        Movies movie = new Movies(0, title, genre, releaseDate.toString(), duration, null);
        movie.setImagePath(ImageView.getImage().getUrl());

        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO movies (title, genre, duration, release_date, image) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDuration());
            stmt.setString(5, movie.getImagePath());
            stmt.setDate(4, Date.valueOf(movie.getReleaseDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadMoviesFromDatabase();
    }

}