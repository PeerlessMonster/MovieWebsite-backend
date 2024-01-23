package cn.edu.scnu.DAO;

import cn.edu.scnu.entity.TopScoreMovie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopScoreMovieDAO extends BaseMapper<TopScoreMovie> {
}
