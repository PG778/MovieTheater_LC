package com.example.movietheaterbookingclassprojectlc;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Movies{
    private int id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty releaseDate;
    private final SimpleStringProperty duration;
    private String imagePath;

    public Movies(int id, String title, String genre, String releaseDate, String duration, String imagePath) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.releaseDate = new SimpleStringProperty(releaseDate);
        this.duration = new SimpleStringProperty(duration);
        this.imagePath = imagePath;
    }

    public Movies(String title, String genre, int duration, LocalDate releaseDate) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.releaseDate = new SimpleStringProperty(releaseDate.toString());
        this.duration = new SimpleStringProperty(String.valueOf(duration));
    }

    public Movies(String title, String genre, int year) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.releaseDate = new SimpleStringProperty(String.valueOf(year));
        this.duration = new SimpleStringProperty("");
    }

    public Movies() {
        this.title = new SimpleStringProperty("");
        this.genre = new SimpleStringProperty("");
        this.releaseDate = new SimpleStringProperty("");
        this.duration = new SimpleStringProperty("");
    }

    public Movies(String title, String genre) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.releaseDate = new SimpleStringProperty("");
        this.duration = new SimpleStringProperty("");
    }

    public Movies(String title, String genre, String duration, String releaseDate, Image image) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.releaseDate = new SimpleStringProperty(releaseDate);
        this.duration = new SimpleStringProperty(duration);
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title.get();
    }
    public String getGenre(){
        return genre.get();
    }
    public String getReleaseDate(){
        return releaseDate.get();
    }
    public String getDuration(){
        return duration.get();
    }
    public String getImagePath() {
        return imagePath;
    }

    // Setters
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title.set(title);
    }
    public void setGenre(String genre){
        this.genre.set(genre);
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate.set(releaseDate);
    }
    public void setDuration(String duration){
        this.duration.set(duration);
    }
    public void setImagePath(String image) {
        this.imagePath = image;
    }

    // Property methods
    public ObservableValue<String> titleProperty() {
        return title;
    }
    public ObservableValue<String> genreProperty() {
        return genre;
    }
    public SimpleStringProperty releaseDateProperty() {
        return releaseDate;
    }

    // Property methods
    public ObservableValue<String> durationProperty() {
        return duration;
    }
}