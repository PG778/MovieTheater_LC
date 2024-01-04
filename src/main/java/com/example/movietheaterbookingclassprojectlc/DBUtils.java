package com.example.movietheaterbookingclassprojectlc;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class DBUtils {

    public static User user = null;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tickets", "root", "password");
    }

    public static void changeScene(String fxmlFile, String _title) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            Parent root = loader.load();
            String toClose = fxmlFile;



            Stage stage = new Stage();


            stage.setTitle(_title);
            stage.setResizable(false);
            stage.setScene(new Scene(root, 600, 400));

            stage.show();
            createSnowfall((Pane) root);
            stage.setOnCloseRequest(e -> {
                try {
                    if (toClose.equals("Homepage.fxml")) {
                       stage.close();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean validatePassword(String username, String password) throws SQLException {

        //Retrieve all accounts
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tickets", "root", "password");
        PreparedStatement getAccounts = conn.prepareStatement("SELECT * FROM Admin WHERE username = ?");
        getAccounts.setString(1, username);
        ResultSet accounts = getAccounts.executeQuery();

        while (accounts.next()) {
            String retrievedPassword = accounts.getString("password");

            if (retrievedPassword.equals(password)) {

                return true;
            }
        }






        conn.close();
        return false;
    }


    public static void loginUser(String username, String password, String fxmlFile, String title) throws SQLException, IOException {
        if (validatePassword(username, password)) {
            user = new User(username, password); // Assuming 0 as a placeholder for the user ID
            changeScene(fxmlFile, title);
        } else {
            System.out.println("Invalid username or password");
        }
    }


    public static void signUpUser(String _username, String _pwd) throws SQLException {

        Connection conn;
        PreparedStatement psCheckIfUserExists;
        PreparedStatement psInsertUser = null;
        ResultSet resultSet;

        // SELECT <all records> FROM <table name> WHERE <column matches parameter>)
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tickets", "root", "password");
        psCheckIfUserExists = conn.prepareStatement("SELECT * FROM admin WHERE username = ?");
        psCheckIfUserExists.setString(1, _username);
        resultSet = psCheckIfUserExists.executeQuery();

        // User already exists
        if (resultSet.isBeforeFirst()) {
            System.out.println("User already exist!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This user already exists!");
            alert.show();
        }
        // User does not exist
        else {
            psInsertUser = conn.prepareStatement("INSERT INTO admin (username, password) VALUES (?, ?)");
            psInsertUser.setString(1, _username);
            psInsertUser.setString(2, _pwd);
            psInsertUser.executeUpdate();






            psInsertUser.close();
        }

        resultSet.close();
        assert psInsertUser != null;
        psCheckIfUserExists.close();
        conn.close();

    }
    public static void createSnowfall(Pane root) {
        for (int i = 0; i < SNOWFLAKES_COUNT; i++) {
            Circle snowflake = new Circle(1, 1, 1);
            snowflake.setRadius(random.nextDouble() * 3);
            snowflake.setFill(Color.WHITE);

            TranslateTransition fall = new TranslateTransition();
            fall.setNode(snowflake);
            fall.setFromY(-200);
            fall.setToY(600 + 200);
            fall.setFromX(0 + random.nextDouble() * 200);
            fall.setToX(0 + random.nextDouble() * 200);
            fall.setCycleCount(Animation.INDEFINITE);
            fall.setDuration(Duration.seconds(5 + random.nextDouble() * 5));
            fall.play();

            root.getChildren().add(snowflake);
        }
    }
    private static final int SNOWFLAKES_COUNT = 300;
    private static Random random = new Random();
}



