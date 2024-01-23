package cn.edu.scnu.service;

import cn.edu.scnu.DAO.MovieDetailDAO;
import cn.edu.scnu.VO.MovieBriefIntroVO;
import cn.edu.scnu.entity.MovieDetail;
import cn.edu.scnu.VO.MovieDetailVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDetailService extends ServiceImpl<MovieDetailDAO, MovieDetail> {
    @Cacheable(cacheNames = "movie", key = "#id")
    public MovieDetailVO selectById(int id) {
        MovieDetail movie = this.getById(id);
        return MovieDetailVO.transform(movie);
    }

    public List<MovieBriefIntroVO> selectWhere(List<String> categoryList, List<String> regionList, String searchColumn, String keyword, String sortColumn, boolean isDescend, int offset, int limit) {
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

        List<MovieBriefIntroVO> movieResponseList = new ArrayList<>();
        for (MovieDetail movie: movieList) {
            MovieBriefIntroVO movieResponse = new MovieBriefIntroVO(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
