package cn.edu.scnu.VO;

import cn.edu.scnu.DTO.MovieBriefResponse;
import cn.edu.scnu.util.TimestampFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MovieBriefVO implements Serializable {
    private static final long serialVersionUID = -7441574992405185854L;

    private int id;
    private String name;
    private String category;
    private String releaseTime;
    private int playAmount;
    private float score;

    public MovieBriefVO(MovieBriefResponse movie) {
        Timestamp timestamp = movie.getReleaseTime();
        String releaseTime = TimestampFormat.formatString(timestamp);

        this.id = movie.getId();
        this.name = movie.getName();
        this.category = movie.getCategory();
        this.releaseTime = releaseTime;
        this.playAmount = movie.getPlayAmount();
        this.score = movie.getScore();
    }
}
