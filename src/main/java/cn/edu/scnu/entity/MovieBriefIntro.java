package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("PopularMovieView")
public class MovieBriefIntro {
    @TableId()
    private Integer id;
    private String name;
    private String category;
    private Timestamp releaseTime;
    private int playAmount;
    private float score;
}
