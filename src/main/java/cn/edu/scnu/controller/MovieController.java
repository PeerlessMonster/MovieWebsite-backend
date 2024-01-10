package cn.edu.scnu.controller;

import cn.edu.scnu.model.movie.*;
import cn.edu.scnu.service.CarouselMovieService;
import cn.edu.scnu.service.LatestMovieService;
import cn.edu.scnu.service.PopularMovieService;
import cn.edu.scnu.service.TopScoreMovieService;
import org.springframework.web.bind.annotation.GetMapping;
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

    public MovieController(CarouselMovieService carouselMovieService, LatestMovieService latestMovieService, TopScoreMovieService topScoreMovieService, PopularMovieService popularMovieService) {
        this.carouselMovieService = carouselMovieService;
        this.latestMovieService = latestMovieService;
        this.topScoreMovieService = topScoreMovieService;
        this.popularMovieService = popularMovieService;
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
}
