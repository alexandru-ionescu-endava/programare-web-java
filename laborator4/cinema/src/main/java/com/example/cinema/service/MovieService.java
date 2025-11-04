package com.example.cinema.service;

import com.example.cinema.model.Movie;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Service
public class MovieService {

    private List<Movie> movieList = new ArrayList<>();

    public MovieService() {
        initializeList();
    }

    private void initializeList() {
        movieList.add(
                new Movie(1, "Inception", 148)
        );
        movieList.add(
                new Movie(2, "The Dark Knight", 152)
        );
        movieList.add(
                new Movie(3, "Interstellar", 169)
        );
    }

    public void addMovie(Movie movie) {
        movie.setId(new Random().nextInt());
        movieList.add(movie);
    }

    public void deleteMovieById(Integer id) {
        movieList.removeIf(movie -> movie.getId().equals(id));
    }
}
