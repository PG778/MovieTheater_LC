package com.example.movietheaterbookingclassprojectlc;

import java.sql.*;

public class MovieDB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/movie_tickets";
    static final String USER = "root";
    static final String PASS = "password";

    public static void fetchMovies() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT title, genre, duration, screening FROM Movies";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String title  = rs.getString("title");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                boolean screening = rs.getBoolean("release_date");

                // process data
            }
            rs.close();
        } catch(Exception se) {
            se.printStackTrace();
        }
    }

    public static void updateMovie(String title, boolean screening) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "UPDATE Movies SET release_date = " + screening + "WHERE title = " + title;
            stmt.executeUpdate(sql);
        } catch(Exception se) {
            se.printStackTrace();
        }
    }

    public static void deleteMovie(String title) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "DELETE FROM Movies WHERE title = " + title;
            stmt.executeUpdate(sql);
        } catch(Exception se) {
            se.printStackTrace();
        }
    }
}