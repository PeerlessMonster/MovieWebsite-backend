package cn.edu.scnu.dao;

import cn.edu.scnu.model.movie.LatestMovie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LatestMovieDao extends BaseMapper<LatestMovie> {
}
