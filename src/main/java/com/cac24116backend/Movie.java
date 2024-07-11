package com.cac24116backend;

public class Movie {

    private int id;
    private String title;
    private String genre;
    private String cast;
    private String director;
    private String duration;
    private String synapsis;
    private String available;


    public Movie() {}

    public Movie(int id, String title, String genre, String cast, String director, String duration,
                 String synapsis, String available) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.cast = cast;
        this.director = director;
        this.duration = duration;
        this.synapsis = synapsis;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSynapsis() {
        return synapsis;
    }

    public void setSynapsis(String synapsis) {
        this.synapsis = synapsis;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
