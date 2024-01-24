package cn.edu.scnu.VO;

import cn.edu.scnu.DTO.MovieDetailResponse;
import cn.edu.scnu.util.TimestampFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MovieDetailVO implements Serializable {
    private static final long serialVersionUID = 3449632624109750528L;

    private Integer id;
    private String name;
    private String category;
    private String region;
    private String director;
    private String scriptwriter;
    private String actor;
    private String releaseTime;
    private int duration;
    private String description;
    private int playAmount;
    private float score;
    private int vip;

    public MovieDetailVO(MovieDetailResponse movie) {
        Timestamp timestamp = movie.getReleaseTime();
        String releaseTime = TimestampFormat.formatString(timestamp);

        this.id = movie.getId();
        this.name = movie.getName();
        this.category = movie.getCategory();
        this.region = movie.getRegion();
        this.director = movie.getDirector();
        this.scriptwriter = movie.getScriptwriter();
        this.actor = movie.getActor();
        this.releaseTime = releaseTime;
        this.duration = movie.getDuration();
        this.description = movie.getDescription();
        this.playAmount = movie.getPlayAmount();
        this.score = movie.getScore();
        this.vip = movie.getVip();
    }
}
