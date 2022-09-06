package com.erick.movies.data;

import com.erick.movies.domain.Movie;
import com.erick.movies.exceptions.*;
import java.util.List;

public interface DataAccess {

    boolean exists(String fileName);

    void create(String fileName) throws ExDataAccess;

    void write(Movie movie, String fileName, boolean append) throws ExDataWriting;

    void delete(String fileName);

    String find(String fileName, String content) throws ExDataReading;

    List<Movie> list(String fileName) throws ExDataReading;
}
