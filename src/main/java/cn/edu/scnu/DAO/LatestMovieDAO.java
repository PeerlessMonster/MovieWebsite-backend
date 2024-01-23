package cn.edu.scnu.DAO;

import cn.edu.scnu.entity.LatestMovie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LatestMovieDAO extends BaseMapper<LatestMovie> {
}
