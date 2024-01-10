package cn.edu.scnu.service;

import cn.edu.scnu.dao.PopularMovieDao;
import cn.edu.scnu.model.movie.MovieBriefIntroResponse;
import cn.edu.scnu.model.movie.PopularMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopularMovieService extends ServiceImpl<PopularMovieDao, PopularMovie> {
    @Cacheable(cacheNames = "popular_movie")
    public List<MovieBriefIntroResponse> selectAll() {
        List<MovieBriefIntroResponse> responseMovieList = new ArrayList<>();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        List<PopularMovie> popularMovies = this.list();
        for (PopularMovie popularMovie: popularMovies) {
            Timestamp timestamp = popularMovie.getReleaseTime();
            String releaseTime = sdf.format(timestamp);

            MovieBriefIntroResponse responseMovie = new MovieBriefIntroResponse(
                    popularMovie.getId(),
                    popularMovie.getName(),
                    popularMovie.getCategory(),
                    releaseTime,
                    popularMovie.getPlayAmount(),
                    popularMovie.getScore()
            );
            responseMovieList.addLast(responseMovie);
        }
        return responseMovieList;
    }
}
