package cn.edu.scnu.service;

import cn.edu.scnu.dao.MovieDetailDao;
import cn.edu.scnu.model.movie.MovieBriefIntro;
import cn.edu.scnu.model.movie.MovieBriefIntroResponse;
import cn.edu.scnu.model.movie.MovieDetail;
import cn.edu.scnu.model.movie.MovieDetailResponse;
import cn.edu.scnu.util.TimestampFormat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDetailService extends ServiceImpl<MovieDetailDao, MovieDetail> {
    @Cacheable(cacheNames = "movie", key = "#id")
    public MovieDetailResponse selectById(int id) {
        MovieDetail movie = this.getById(id);
        return MovieDetailResponse.transform(movie);
    }

    public List<MovieBriefIntroResponse> selectWhere(List<String> categoryList, List<String> regionList, String searchColumn, String keyword, String sortColumn, boolean isDescend, int offset, int limit) {
        QueryWrapper<MovieDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "category", "release_time", "play_amount", "score");

        if (categoryList != null) {
            for (String category: categoryList) {
                queryWrapper.like("category", category);
            }
        }
        if (regionList != null) {
            for (String region: regionList) {
                queryWrapper.like("region", region);
            }
        }
        if (keyword != null && searchColumn != null) {
            queryWrapper.like(searchColumn, keyword);
        }
        queryWrapper.orderBy(true, !isDescend, "id", sortColumn);
        queryWrapper.last("LIMIT " + offset + "," + limit);

        List<MovieDetail> movieList = this.list(queryWrapper);
        if (movieList == null) {
            return null;
        }

        List<MovieBriefIntroResponse> movieResponseList = new ArrayList<>();
        for (MovieDetail movie: movieList) {
            MovieBriefIntroResponse movieResponse = new MovieBriefIntroResponse(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
