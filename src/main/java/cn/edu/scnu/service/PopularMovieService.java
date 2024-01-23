package cn.edu.scnu.service;

import cn.edu.scnu.DAO.PopularMovieDAO;
import cn.edu.scnu.entity.MovieBriefIntro;
import cn.edu.scnu.VO.MovieBriefIntroVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularMovieService extends ServiceImpl<PopularMovieDAO, MovieBriefIntro> {
    @Cacheable(cacheNames = "popular_movie")
    public List<MovieBriefIntroVO> selectAll() {
        List<MovieBriefIntro> movieList = this.list();

        List<MovieBriefIntroVO> movieResponseList = new ArrayList<>();
        for (MovieBriefIntro movie: movieList) {
            MovieBriefIntroVO movieResponse = new MovieBriefIntroVO(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
