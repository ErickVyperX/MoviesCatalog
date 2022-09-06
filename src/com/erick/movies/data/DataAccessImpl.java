package com.erick.movies.data;

import com.erick.movies.domain.Movie;
import com.erick.movies.exceptions.*;
import java.io.*;
import java.util.*;

public class DataAccessImpl implements DataAccess {

    @Override
    public boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    @Override
    public void create(String fileName) throws ExDataAccess {
        File file = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.close();
            System.out.println("Your file has been created!");
        } catch (FileNotFoundException e) {
            throw new ExDataAccess("Create file issue: " + e.getMessage());
        }

    }

    @Override
    public void write(Movie movie, String fileName, boolean append) throws ExDataWriting {
        File file = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file, append));
            pw.println(movie.toString());
            pw.close();
            System.out.println("Your file has been modified!");
        } catch (IOException e) {
            throw new ExDataWriting("Write file issue: " + e.getMessage());
        }
    }

    @Override
    public void delete(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Your file has been deleted!");
            } else {
                System.out.println("Your file can't be deleted!");
            }
        } else {
            System.out.println("Your file hasn't been found!");
        }
    }

    @Override
    public String find(String fileName, String content) throws ExDataReading {
        File file = new File(fileName);
        try {
            var br = new BufferedReader(new FileReader(file));
            var line = br.readLine();
            int numberLine = 0;
            while (line != null) {
                numberLine++;
                line = line.toLowerCase();
                if (line.contains(content.toLowerCase())) {
                    return String.format("%s %d %c", "Your content has been found in line number ", numberLine, '!');
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new ExDataReading("Find content issue: " + e.getMessage());
        }
        return "Your content hasn't been found!";
    }

    @Override
    public List<Movie> list(String fileName) throws ExDataReading {
        List<Movie> list = new ArrayList<>();
        File file = new File(fileName);
        int idCounter = 1;
        if (file.exists()) {
            try {
                var br = new BufferedReader(new FileReader(file));
                var line = br.readLine();
                while (line != null) {
                    list.add(new Movie(idCounter++, line.substring(3)));
                    line = br.readLine();
                }
            } catch (IOException e) {
                throw new ExDataReading("List movies issue: " + e.getMessage());
            }
        }
        return list;
    }
}
