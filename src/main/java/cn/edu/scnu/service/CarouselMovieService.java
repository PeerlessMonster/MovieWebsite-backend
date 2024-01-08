package cn.edu.scnu.service;

import cn.edu.scnu.dao.CarouselMovieDao;
import cn.edu.scnu.model.movie.CarouselMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselMovieService extends ServiceImpl<CarouselMovieDao, CarouselMovie> {
    @Cacheable(cacheNames = "carousel_movie")
    public List<CarouselMovie> selectAll() {
        return this.list();
    }
}
