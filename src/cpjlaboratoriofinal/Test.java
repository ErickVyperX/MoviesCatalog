package cpjlaboratoriofinal;

import com.erick.movies.business.*;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        String movieName, lineOption;
        MoviesCatalog moviesCatalog = new MoviesCatalogImpl();
        do {
            System.out.println("""
                    Welcome to your Movies Catalog. Please, enter a number option:
                    1- Start a new file
                    2- Add a movie
                    3- Find a movie
                    4- List all movies
                    5- Exit""");
            lineOption = scanner.nextLine();
            option = lineOption.matches("[0-9]*")
                    ? Integer.parseInt(lineOption)
                    : -1;
            switch (option) {
                case 1 -> moviesCatalog.startFile();
                case 2 -> {
                    System.out.println("Enter a movie name:");
                    movieName = scanner.nextLine();
                    moviesCatalog.addMovie(movieName);
                }
                case 3 -> {
                    System.out.println("Enter a movie name:");
                    movieName = scanner.nextLine();
                    moviesCatalog.findMovie(movieName);
                }
                case 4 -> moviesCatalog.listMovies();
                case 5 -> System.out.println("Bye Bye!");
                default -> System.out.println("Invalid Option!");
            }
        } while (option != 5);
    }
}
