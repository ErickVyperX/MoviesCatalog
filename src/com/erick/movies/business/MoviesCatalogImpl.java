package com.erick.movies.business;

import com.erick.movies.data.*;
import com.erick.movies.domain.Movie;
import com.erick.movies.exceptions.*;

public class MoviesCatalogImpl implements MoviesCatalog {
    private final DataAccess dataAccess;

    public MoviesCatalogImpl() {
        this.dataAccess = new DataAccessImpl();
    }

    @Override
    public void addMovie(String movieName) {
        if (dataAccess.exists(FILE_PATH)) {
            Movie movie = new Movie(movieName);
            var append = dataAccess.exists(FILE_PATH);
            try {
                dataAccess.write(movie, FILE_PATH, append);
            } catch (ExDataWriting e) {
                System.out.println("Data Access Error!");
            }
        } else {
            System.out.println("File not found!");
        }
    }

    @Override
    public void listMovies() {
        if (dataAccess.exists(FILE_PATH)) {
            try {
                var movies = dataAccess.list(FILE_PATH);
                for (var movie : movies) {
                    System.out.println(movie);
                }
            } catch (ExDataReading e) {
                System.out.println("Data Access Error!");
            }
        } else {
            System.out.println("File not found!");
        }
    }

    @Override
    public void findMovie(String movieName) {
        if (dataAccess.exists(FILE_PATH)) {
            try {
                System.out.println(dataAccess.find(FILE_PATH, movieName));
            } catch (ExDataReading e) {
                System.out.println("Data Access Error!");
            }
        } else {
            System.out.println("File not found!");
        }

    }

    @Override
    public void startFile() {
        if (dataAccess.exists(FILE_PATH)) {
            dataAccess.delete(FILE_PATH);
        }
        try {
            dataAccess.create(FILE_PATH);
        } catch (ExDataAccess e) {
            System.out.println("Data Access Error!");
        }
    }
}
