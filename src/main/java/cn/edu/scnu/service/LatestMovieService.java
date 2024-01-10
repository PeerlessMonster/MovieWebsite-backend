package cn.edu.scnu.service;

import cn.edu.scnu.dao.LatestMovieDao;
import cn.edu.scnu.model.movie.LatestMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatestMovieService extends ServiceImpl<LatestMovieDao, LatestMovie> {
    @Cacheable(cacheNames = "latest_movie")
    public List<LatestMovie> selectAll() {
        return this.list();
    }
}
