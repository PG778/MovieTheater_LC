package com.example.movietheaterbookingclassprojectlc;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Movies {
    private int id;
    private String title;
    private String genre;

    private String releaseDate;
    private String duration;
    private String imagePath;

    public Movies(int id, String title, String genre,
                   String releaseDate, String duration,
                  String imagePath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.imagePath = imagePath;
    }

    public ObservableValue<String> genreProperty() {
        return new SimpleStringProperty(genre);
    }

    public ObservableValue<String> durationProperty() {
        return new SimpleStringProperty(duration);
    }

    public ObservableValue<String> releaseDateProperty() {
        return new SimpleStringProperty(releaseDate);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public String getDuration(){
        return duration;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }


    public ObservableValue<String> titleProperty() {
        return new SimpleStringProperty(title);
    }

}