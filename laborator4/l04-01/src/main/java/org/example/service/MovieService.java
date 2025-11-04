package org.example.service;

import org.example.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Component
@Service
public class MovieService {

    private List<Movie> movieList = new ArrayList<>();

    public MovieService() {
        initializeMovieList();
    }

    private void initializeMovieList() {
        movieList.add(new Movie(
                1,
                "The Shawshank Redemption",
                142
        ));
        movieList.add(new Movie(
                2,
                "The Godfather",
                175
        ));
        movieList.add(new Movie(
                3,
                "The Dark Knight",
                152
        ));
    }

    public void printMovies() {
        System.out.println("This week:");
        movieList.forEach(System.out::println);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public Movie findByName(String name) {
        return movieList.stream()
                .filter(movie -> movie.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateMovie(String name, Integer duration) {
        Movie movie = findByName(name);
        if (movie != null) {
            movie.setDuration(duration);
        }
    }

    public void deleteMovie(Movie movie) {
        movieList.remove(movie);
    }

//    nu are ce cauta
    public String getFood() {
        return "Popcorn";
    }
}
