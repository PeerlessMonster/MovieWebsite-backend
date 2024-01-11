package cn.edu.scnu.service;

import cn.edu.scnu.dao.MovieDetailDao;
import cn.edu.scnu.model.movie.MovieDetail;
import cn.edu.scnu.model.movie.MovieDetailResponse;
import cn.edu.scnu.util.TimestampFormat;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MovieDetailService extends ServiceImpl<MovieDetailDao, MovieDetail> {
    private MovieDetailDao movieDetailDao;

    public MovieDetailService(MovieDetailDao movieDetailDao) {
        this.movieDetailDao = movieDetailDao;
    }

    @Cacheable(cacheNames = "movie", key = "#id")
    public MovieDetailResponse selectById(int id) {
        MovieDetail movieDetail = this.getById(id);

        Timestamp timestamp = movieDetail.getReleaseTime();
        String releaseTime = TimestampFormat.formatString(timestamp);

        return new MovieDetailResponse(
                movieDetail.getId(),
                movieDetail.getName(),
                movieDetail.getCategory(),
                movieDetail.getRegion(),
                movieDetail.getDirector(),
                movieDetail.getScriptwriter(),
                movieDetail.getActor(),
                releaseTime,
                movieDetail.getDuration(),
                movieDetail.getDescription(),
                movieDetail.getPlayAmount(),
                movieDetail.getScore(),
                movieDetail.getVip()
        );
    }
}
