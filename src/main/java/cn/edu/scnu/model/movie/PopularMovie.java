package cn.edu.scnu.model.movie;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("PopularMovieView")
public class PopularMovie {
    @TableId()
    private Integer id;
    private String name;
    private String category;
    private Timestamp releaseTime;
    private int playAmount;
    private float score;
}
