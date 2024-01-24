package cn.edu.scnu.service;

import cn.edu.scnu.DAO.MovieDAO;
import cn.edu.scnu.VO.MovieBriefVO;
import cn.edu.scnu.entity.Movie;
import cn.edu.scnu.VO.MovieDetailVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService extends ServiceImpl<MovieDAO, Movie> {
    private List<Movie> distinctSelectColumn(String columnName) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT " + columnName);
        return this.list(queryWrapper);
    }


    @Cacheable(cacheNames = "movie_category")
    public Set<String> selectCategory() {
        Set<String> categorySet = new HashSet<>();

        List<Movie> movieList = distinctSelectColumn("category");
        for (Movie movie: movieList) {
            String categoryStr = movie.getCategory();
            String[] categoryList = categoryStr.split("/");
            for (String category: categoryList) {
                categorySet.add(category);
            }
        }
        return categorySet;
    }

    @Cacheable(cacheNames = "movie_region")
    public Set<String> selectRegion() {
        Set<String> regionSet = new HashSet<>();

        List<Movie> movieList = distinctSelectColumn("region");
        for (Movie movie: movieList) {
            String regionStr = movie.getRegion();
            String[] regionList = regionStr.split("/");
            for (String category: regionList) {
                regionSet.add(category);
            }
        }
        return regionSet;
    }

    public MovieDetailVO selectById(int id) {
        Movie movie = this.getById(id);
        return new MovieDetailVO(movie);
    }

    public List<MovieBriefVO> selectWhere(List<String> categoryList, List<String> regionList, String searchColumn, String keyword, String sortColumn, boolean isDescend, int offset, int limit) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
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
        /* 第一个true表示开启排序 */
        queryWrapper.orderBy(true, !isDescend, sortColumn);
        queryWrapper.last("LIMIT " + offset + "," + limit);

        List<Movie> movieList = this.list(queryWrapper);
        if (movieList == null) {
            return null;
        }

        List<MovieBriefVO> movieResponseList = new ArrayList<>();
        for (Movie movie: movieList) {
            MovieBriefVO movieResponse = new MovieBriefVO(movie);
            movieResponseList.addLast(movieResponse);
        }
        return movieResponseList;
    }
}
