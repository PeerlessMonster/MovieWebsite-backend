package cn.edu.scnu.controller;

import cn.edu.scnu.DTO.ErrorResponse;
import cn.edu.scnu.DTO.SearchRequest;
import cn.edu.scnu.VO.ErrorVO;
import cn.edu.scnu.VO.MovieBriefVO;
import cn.edu.scnu.VO.MovieDetailVO;
import cn.edu.scnu.entity.CarouselMovie;
import cn.edu.scnu.entity.LatestMovie;
import cn.edu.scnu.entity.TopScoreMovie;
import cn.edu.scnu.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MovieController {
    private CarouselMovieService carouselMovieService;
    private LatestMovieService latestMovieService;
    private TopScoreMovieService topScoreMovieService;
    private PopularMovieService popularMovieService;
    private MovieService movieService;

    public MovieController(CarouselMovieService carouselMovieService, LatestMovieService latestMovieService, TopScoreMovieService topScoreMovieService, PopularMovieService popularMovieService, MovieService movieService) {
        this.carouselMovieService = carouselMovieService;
        this.latestMovieService = latestMovieService;
        this.topScoreMovieService = topScoreMovieService;
        this.popularMovieService = popularMovieService;
        this.movieService = movieService;
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
    public List<MovieBriefVO> getPopular(HttpServletResponse response) {
        response.setStatus(200);
        return popularMovieService.selectAll();
    }

    @GetMapping("/movies/{id}")
    public MovieDetailVO searchOne(@PathVariable("id") int id, HttpServletResponse response) {
        response.setStatus(200);
        return movieService.selectById(id);
    }

    @PostMapping("/movies")
    public Object search(@RequestBody SearchRequest request, int offset, int limit, HttpServletResponse response) {
        List<String> categoryList = request.getCategory();
        List<String> regionList = request.getRegion();
        String searchColumn = request.getSearch();
        String keyword = request.getKeyword();
        String sortColumn = request.getSort();
        boolean isDescend = request.isDescend();

        List<MovieBriefVO> movieResponseList = movieService.selectWhere(categoryList, regionList, searchColumn, keyword, sortColumn, isDescend, offset, limit);
        if (movieResponseList == null) {
            response.setStatus(404);
            return new ErrorVO(ErrorResponse.MOVIE_NOT_EXIST);
        }
        response.setStatus(200);
        return movieResponseList;
    }

    @GetMapping("/movies/categories")
    public Set<String> getCategory(HttpServletResponse response) {
        response.setStatus(200);
        return movieService.selectCategory();
    }

    @GetMapping("/movies/regions")
    public Set<String> getRegion(HttpServletResponse response) {
        response.setStatus(200);
        return movieService.selectRegion();
    }
}
