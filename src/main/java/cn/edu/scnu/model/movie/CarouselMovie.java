package cn.edu.scnu.model.movie;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("CarouselMovieView")
public class CarouselMovie implements Serializable {
    private static final long serialVersionUID = -8319055271091465535L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int movieId;
    private String name;
}
