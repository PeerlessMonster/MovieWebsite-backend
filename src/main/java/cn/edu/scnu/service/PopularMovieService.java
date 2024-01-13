package cn.edu.scnu.service;

import cn.edu.scnu.dao.PopularMovieDao;
import cn.edu.scnu.model.movie.MovieBriefIntro;
import cn.edu.scnu.model.movie.MovieBriefIntroResponse;
import cn.edu.scnu.model.movie.MovieDetailResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularMovieService extends ServiceImpl<PopularMovieDao, MovieBriefIntro> {
    @Cacheable(cacheNames = "popular_movie")
    public List<MovieBriefIntroResponse> selectAll() {
        List<MovieBriefIntro> movieList = this.list();

        List<MovieBriefIntroResponse> movieResponseList = new ArrayList<>();
        for (MovieBriefIntro movie: movieList) {
            MovieBriefIntroResponse movieResponse = new MovieBriefIntroResponse(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
