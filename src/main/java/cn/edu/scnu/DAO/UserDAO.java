package cn.edu.scnu.DAO;

import cn.edu.scnu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<User> {
}
