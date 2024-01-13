package cn.edu.scnu.model.movie;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("movie")
public class MovieDetail extends MovieBriefIntro {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String category;
    private String region;
    private String director;
    private String scriptwriter;
    private String actor;
    private Timestamp releaseTime;
    private int duration;
    private String description;
    private int playAmount;
    private float score;
    private int vip;
}
