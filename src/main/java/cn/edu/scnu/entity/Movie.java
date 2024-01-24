package cn.edu.scnu.entity;

import cn.edu.scnu.DTO.MovieBriefResponse;
import cn.edu.scnu.DTO.MovieDetailResponse;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Movie implements MovieBriefResponse, MovieDetailResponse {
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
