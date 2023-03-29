package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.MovieInfo;
import com.learnreactiveprogramming.domain.Review;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
public class MovieReactiveService {

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;

    public Flux<Movie> getAllMovies() {

        // 1) Retrieve movieInfoId (Flux<MovieInfo> has the ID)
        // 2) Use such ID to find Reviews. (Mono<List<Review>> will hold the Review List)
        // 3) Build Movie with the information we have. (Movie will hold movieInfo + ReviewList)

        //1 )
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMovieInfoFlux();

       return  movieInfoFlux
                .flatMap(movieInfo -> {
                  //2)
                  Mono<List< Review >> reviewsMono =
                          reviewService.retrieveReviewFlux(movieInfo.getMovieInfoId())
                                  .collectList();
                  // 3)
                  return reviewsMono.map(reviewsList -> new Movie(movieInfo, reviewsList));
                })
                .log();
    }


}
