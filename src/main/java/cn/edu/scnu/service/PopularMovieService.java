package cn.edu.scnu.service;

import cn.edu.scnu.DAO.PopularMovieDAO;
import cn.edu.scnu.entity.PopularMovie;
import cn.edu.scnu.VO.MovieBriefVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularMovieService extends ServiceImpl<PopularMovieDAO, PopularMovie> {
    @Cacheable(cacheNames = "popular_movie")
    public List<MovieBriefVO> selectAll() {
        List<PopularMovie> movieList = this.list();

        List<MovieBriefVO> movieResponseList = new ArrayList<>();
        for (PopularMovie movie: movieList) {
            MovieBriefVO movieResponse = new MovieBriefVO(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
