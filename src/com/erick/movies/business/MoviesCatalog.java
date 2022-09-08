package com.erick.movies.business;

public interface MoviesCatalog {

    String FILE_PATH = "D://Test-Programming//MoviesCatalog.txt";

    void addMovie(String movieName);

    void listMovies();

    void findMovie(String movieName);

    void startFile();
}
