package com.example.movietheaterbookingclassprojectlc;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    private static final int SNOWFLAKES_COUNT = 300;
    private Random random = new Random();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Movie Theater Tickets!");
        stage.setScene(scene);
        stage.show();

        createSnowfall(root);
    }

    public void createSnowfall(Pane root) {
        for (int i = 0; i < SNOWFLAKES_COUNT; i++) {
            Circle snowflake = new Circle(1, 1, 1);
            snowflake.setRadius(random.nextDouble() * 3);
            snowflake.setFill(Color.WHITE);

            TranslateTransition fall = new TranslateTransition();
            fall.setNode(snowflake);
            fall.setFromY(-200);
            fall.setToY(600 + 200);
            fall.setFromX(random.nextDouble() * root.getWidth());
            fall.setToX(random.nextDouble() * root.getWidth());
            fall.setCycleCount(Animation.INDEFINITE);
            fall.setDuration(Duration.seconds(5 + random.nextDouble() * 5));
            fall.play();

            root.getChildren().add(snowflake);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}