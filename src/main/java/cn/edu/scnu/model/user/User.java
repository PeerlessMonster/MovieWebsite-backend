package cn.edu.scnu.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1177319214665217813L;

    @TableId(type = IdType.AUTO)
    /* 解决 This primary key of "id" is primitive !不建议如此请使用包装类 */
    private Integer id;
//    private int id; 错误
    private String passwordHash;
    private String email;
    private String name;
    private int vip;
}
