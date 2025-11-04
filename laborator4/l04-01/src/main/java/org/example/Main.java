package org.example;

import org.example.config.AppConfig;
import org.example.service.MovieService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        var movieService = ctx.getBean(MovieService.class);
        movieService.printMovies();
    }
}