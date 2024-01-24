package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("CarouselMovieView")
public class CarouselMovie implements Serializable {
    private static final long serialVersionUID = -8319055271091465535L;

    @TableId
    private Integer id;
    private String name;
}
