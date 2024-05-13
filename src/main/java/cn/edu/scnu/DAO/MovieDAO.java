package cn.edu.scnu.DAO;

import cn.edu.scnu.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieDAO extends BaseMapper<Movie> {
}
