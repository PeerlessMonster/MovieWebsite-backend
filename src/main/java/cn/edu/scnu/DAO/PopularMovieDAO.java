package cn.edu.scnu.DAO;

import cn.edu.scnu.entity.PopularMovie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PopularMovieDAO extends BaseMapper<PopularMovie> {
}
