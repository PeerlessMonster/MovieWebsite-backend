package cn.edu.scnu.dao;

import cn.edu.scnu.model.movie.PopularMovie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PopularMovieDao extends BaseMapper<PopularMovie> {
}
