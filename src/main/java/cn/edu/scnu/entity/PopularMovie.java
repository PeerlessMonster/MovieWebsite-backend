package cn.edu.scnu.entity;

import cn.edu.scnu.DTO.MovieBriefResponse;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("PopularMovieView")
public class PopularMovie implements MovieBriefResponse {
    @TableId
    private Integer id;
    private String name;
    private String category;
    private Timestamp releaseTime;
    private int playAmount;
    private float score;
}
