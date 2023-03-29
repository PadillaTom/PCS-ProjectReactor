package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieReactiveServiceTest {

    private MovieInfoService movieInfoService = new MovieInfoService();
    private ReviewService reviewService = new ReviewService();
    private MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService);


    @Test
    void getAllMovies() {
        // When:
        Flux<Movie> moviesFlux = movieReactiveService.getAllMovies();

        // Then:
        StepVerifier.create(moviesFlux)
                .assertNext(movie -> {
                    // Name of Movie:
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                    // Reviews Length:
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    // Name of Movie:
                    assertEquals("The Dark Knight", movie.getMovieInfo().getName());
                    // Reviews Length:
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    // Name of Movie:
                    assertEquals("Dark Knight Rises", movie.getMovieInfo().getName());
                    // Reviews Length:
                    assertEquals(2, movie.getReviewList().size());
                })
                .verifyComplete();
    }
}