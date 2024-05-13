package cn.edu.scnu.service;

import cn.edu.scnu.DAO.LatestMovieDAO;
import cn.edu.scnu.entity.LatestMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatestMovieService extends ServiceImpl<LatestMovieDAO, LatestMovie> {
    @Cacheable(cacheNames = "latest_movie")
    public List<LatestMovie> selectAll() {
        return this.list();
    }
}
