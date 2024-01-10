package cn.edu.scnu.service;

import cn.edu.scnu.dao.TopScoreMovieDao;
import cn.edu.scnu.model.movie.TopScoreMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopScoreMovieService extends ServiceImpl<TopScoreMovieDao, TopScoreMovie> {
    @Cacheable(cacheNames = "topscore_movie")
    public List<TopScoreMovie> selectAll() {
        return this.list();
    }
}
