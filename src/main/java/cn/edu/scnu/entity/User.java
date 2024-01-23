package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1177319214665217813L;

    @TableId(type = IdType.AUTO)
//    private int id;
    /* This primary key of "id" is primitive !不建议如此请使用包装类 */
    private Integer id;
    private String passwordHash;
    private String email;
    private String name;
    private int vip;
}
