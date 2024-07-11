package com.cac24116backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private ConnectionDB connection;

    public MovieService() {
        this.connection = new ConnectionDB();
    }

    public List<Movie> getAllAvailableMovies() throws ClassNotFoundException, SQLException{
        List<Movie> movies = new ArrayList<>();
        Connection con = connection.getConnection();
        String sql = "SELECT * FROM movies WHERE available = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("cast"),
                    rs.getString("director"),
                    rs.getString("duration"),
                    rs.getString("synapsis"),
                    rs.getString("available")
            );
            movies.add(movie);
        }
        return movies;
    }

    public List<Movie> getAllMovies() throws ClassNotFoundException, SQLException{
        List<Movie> movies = new ArrayList<>();
        Connection con = connection.getConnection();
        String sql = "SELECT * FROM movies";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("cast"),
                    rs.getString("director"),
                    rs.getString("duration"),
                    rs.getString("synapsis"),
                    rs.getString("available")
            );
            movies.add(movie);
        }
        return movies;
    }

    public Movie getMovieById(int id) throws ClassNotFoundException, SQLException {
        Connection con = connection.getConnection();
        String sql = "SELECT * FROM movies WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Movie movie = null;

        if (rs.next()) {
            movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("cast"),
                    rs.getString("director"),
                    rs.getString("duration"),
                    rs.getString("synapsis"),
                    rs.getString("available")
            );
        }
        return movie;
    }

    public void addMovie(Movie movie) throws ClassNotFoundException, SQLException{
        Connection con = connection.getConnection();
        String sql = "INSERT INTO movies (title, genre, cast, director, duration, synapsis) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, movie.getTitle());
        ps.setString(2, movie.getGenre());
        ps.setString(3, movie.getCast());
        ps.setString(4, movie.getDirector());
        ps.setString(5, movie.getDuration());
        ps.setString(6, movie.getSynapsis());
        ps.executeUpdate();

    }

    public void updateMovie(Movie movie, int id) throws ClassNotFoundException, SQLException{
        Connection con = connection.getConnection();
        String sql = "UPDATE movies SET title = ?, genre = ?, cast = ?, director = ?, duration = ?, synapsis = ?, available = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, movie.getTitle());
        ps.setString(2, movie.getGenre());
        ps.setString(3, movie.getCast());
        ps.setString(4, movie.getDirector());
        ps.setString(5, movie.getDuration());
        ps.setString(6, movie.getSynapsis());
        ps.setString(7, movie.getAvailable());
        ps.setInt(8, id);
        ps.executeUpdate();
    }

    public void deleteMovie(int id) throws ClassNotFoundException, SQLException{
        Connection con = connection.getConnection();
        String sql = "UPDATE movies SET available = '0' WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}

