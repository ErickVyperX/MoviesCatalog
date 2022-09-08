package com.erick.movies.domain;

import java.io.*;
import java.util.StringJoiner;

import static com.erick.movies.business.MoviesCatalog.FILE_PATH;

public class Movie {
    private final int idMovie;
    private String name;
    private static int counterIdMovie;

    //Count lines on file to obtain the next ID
    static {
        File file = new File(FILE_PATH);
        int counter = 1;
        try {
            var br = new BufferedReader(new FileReader(file));
            var line = br.readLine();
            while (line != null) {
                line = br.readLine();
                counter++;
            }
        } catch (IOException e) {
            System.out.println("Obtain last ID Movie issue!");
        }
        counterIdMovie = counter;
    }

    private Movie() {
        this.idMovie = counterIdMovie++;
    }

    public Movie(String name) {
        this();
        this.name = name;
    }

    //Used in list method on DataAccessImpl
    public Movie(int idMovie, String name) {
        this.idMovie = idMovie;
        this.name = name;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(" ", "" , "")
                .add(idMovie + "-")
                .add(name)
                .toString();
    }
}
