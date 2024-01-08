package cn.edu.scnu.model.movie;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("movie")
public class MovieDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String category;
    private String region;
    private String crew;
    private Timestamp releaseTime;
    private Integer duration;
    private float score;
}
