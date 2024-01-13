package cn.edu.scnu.controller;

import cn.edu.scnu.model.ErrorResponse;
import cn.edu.scnu.model.movie.*;
import cn.edu.scnu.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<CarouselMovie> getCarousel(HttpServletResponse response) {
        response.setStatus(200);
        return carouselMovieService.selectAll();
    }

    @GetMapping("/movies/latests")
    public List<LatestMovie> getLatest(HttpServletResponse response) {
        response.setStatus(200);
        return latestMovieService.selectAll();
    }

    @GetMapping("/movies/topscores")
    public List<TopScoreMovie> getTopScore(HttpServletResponse response) {
        response.setStatus(200);
        return topScoreMovieService.selectAll();
    }

    @GetMapping("/movies/populars")
    public List<MovieBriefIntroResponse> getPopular(HttpServletResponse response) {
        response.setStatus(200);
        return popularMovieService.selectAll();
    }

    @GetMapping("/movies/{id}")
    public MovieDetailResponse searchOne(@PathVariable("id") int id, HttpServletResponse response) {
        response.setStatus(200);
        return movieDetailService.selectById(id);
    }

    @PostMapping("/movies")
    public Object search(@RequestBody SearchRequest request, int offset, int limit, HttpServletResponse response) {
        List<String> categoryList = request.getCategory();
        List<String> regionList = request.getRegion();
        String searchColumn = request.getSearch();
        String keyword = request.getKeyword();
        String sortColumn = request.getSort();
        boolean isDescend = request.isDescend();

        List<MovieBriefIntroResponse> movieResponseList = movieDetailService.selectWhere(categoryList, regionList, searchColumn, keyword, sortColumn, isDescend, offset, limit);
        if (movieResponseList == null) {
            response.setStatus(404);
            return new ErrorResponse("没有符合条件的结果！");
        }
        response.setStatus(200);
        return movieResponseList;
    }
}
