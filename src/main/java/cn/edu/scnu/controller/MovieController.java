package cn.edu.scnu.controller;

import cn.edu.scnu.model.movie.*;
import cn.edu.scnu.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MovieController {
    private CarouselMovieService carouselMovieService;
    private LatestMovieService latestMovieService;
    private TopScoreMovieService topScoreMovieService;
    private PopularMovieService popularMovieService;
    private MovieDetailService movieDetailService;

    public MovieController(CarouselMovieService carouselMovieService, LatestMovieService latestMovieService, TopScoreMovieService topScoreMovieService, PopularMovieService popularMovieService, MovieDetailService movieDetailService) {
        this.carouselMovieService = carouselMovieService;
        this.latestMovieService = latestMovieService;
        this.topScoreMovieService = topScoreMovieService;
        this.popularMovieService = popularMovieService;
        this.movieDetailService = movieDetailService;
    }

    @GetMapping("/movies/carousels")
    public List<CarouselMovie> sendCarousel() {
        return carouselMovieService.selectAll();
    }

    @GetMapping("/movies/latests")
    public List<LatestMovie> sendLatest() {
        return latestMovieService.selectAll();
    }

    @GetMapping("/movies/topscores")
    public List<TopScoreMovie> sendTopScore() {
        return topScoreMovieService.selectAll();
    }

    @GetMapping("/movies/populars")
    public List<MovieBriefIntroResponse> sendPopular() {
        return popularMovieService.selectAll();
    }

    @GetMapping("/movies/{id}")
    public MovieDetailResponse sendDetail(@PathVariable("id") int id) {
        return movieDetailService.selectById(id);
    }
}
