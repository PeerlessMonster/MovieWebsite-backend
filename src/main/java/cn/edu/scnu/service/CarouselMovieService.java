package cn.edu.scnu.service;

import cn.edu.scnu.DAO.CarouselMovieDAO;
import cn.edu.scnu.entity.CarouselMovie;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselMovieService extends ServiceImpl<CarouselMovieDAO, CarouselMovie> {
    @Cacheable(cacheNames = "carousel_movie")
    public List<CarouselMovie> selectAll() {
        return this.list();
    }
}
