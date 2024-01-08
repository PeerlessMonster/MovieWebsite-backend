package cn.edu.scnu.controller;

import cn.edu.scnu.model.movie.CarouselMovie;
import cn.edu.scnu.service.CarouselMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    private CarouselMovieService carouselMovieService;

    public MovieController(CarouselMovieService carouselMovieService) {
        this.carouselMovieService = carouselMovieService;
    }

    @GetMapping("/movies/carousels")
    public List<CarouselMovie> sendCarousel() {
        return carouselMovieService.selectAll();
    }
}
