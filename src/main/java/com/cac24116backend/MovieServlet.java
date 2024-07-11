package com.cac24116backend;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/movies/*")
public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        movieService = new MovieService();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Movie> movies = movieService.getAllAvailableMovies();
                String json = objectMapper.writeValueAsString(movies);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else if (pathInfo.equals("/not-available")) {
                List<Movie> movies = movieService.getAllMovies();
                String json = objectMapper.writeValueAsString(movies);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                    int id = Integer.parseInt(pathParts[1]);
                    Movie movie = movieService.getMovieById(id);
                    if (movie != null) {
                        String json = objectMapper.writeValueAsString(movie);
                        resp.setContentType("application/json");
                        resp.getWriter().write(json);
                        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void doPut (HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String pathInfo = req.getPathInfo();

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length > 1) {
            int id = Integer.parseInt(pathParts[1]);
            try{
                Movie movie = movieService.getMovieById(id);
                if (movie != null){
                    movie = objectMapper.readValue(req.getReader(),Movie.class);
                    movieService.updateMovie(movie, id);
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                }
            } catch(SQLException | ClassNotFoundException e){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try{
            Movie movie = objectMapper.readValue(req.getReader(),Movie.class);
            movieService.addMovie(movie);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length > 1) {
            int id = Integer.parseInt(pathParts[1]);
            try{
                Movie movie = movieService.getMovieById(id);
                if (movie != null){
                    movieService.deleteMovie(id);
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                }
            } catch(SQLException | ClassNotFoundException e){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            }
        } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}