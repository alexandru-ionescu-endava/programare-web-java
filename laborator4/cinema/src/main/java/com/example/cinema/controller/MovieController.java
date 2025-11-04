package com.example.cinema.controller;

import com.example.cinema.model.Movie;
import com.example.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/home")
    public String hello(Model model) {
        String message = "Welcome, today is " + LocalDate.now();

        model.addAttribute(
                "welcomeMessage", message
        );

        return "home";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Movie> movies = movieService.getMovieList();

        model.addAttribute("newMovie", new Movie(null, "", null));
        model.addAttribute("movies", movies);

        return "movies";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute("newMovie") Movie movie,
                           Model model) {

        movieService.addMovie(movie);

        model.addAttribute("movies", movieService.getMovieList());

        return "redirect:/movies/findAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id,
                              Model model) {

        movieService.deleteMovieById(id);

        model.addAttribute("movies", movieService.getMovieList());
        model.addAttribute("newMovie", new Movie(null, "", null));

        return "redirect:/movies/findAll";
    }
}
