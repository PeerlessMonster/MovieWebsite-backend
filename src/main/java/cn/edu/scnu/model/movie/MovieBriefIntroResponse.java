package cn.edu.scnu.model.movie;

import cn.edu.scnu.util.TimestampFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MovieBriefIntroResponse implements Serializable {
    private static final long serialVersionUID = -7441574992405185854L;

    private int id;
    private String name;
    private String category;
    private String releaseTime;
    private int playAmount;
    private float score;

    public MovieBriefIntroResponse(int id, String name, String category, String releaseTime, int playAmount, float score) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.releaseTime = releaseTime;
        this.playAmount = playAmount;
        this.score = score;
    }

    public MovieBriefIntroResponse(MovieBriefIntro movieBriefIntro) {
        Timestamp timestamp = movieBriefIntro.getReleaseTime();
        String releaseTime = TimestampFormat.formatString(timestamp);

        this.id = movieBriefIntro.getId();
        this.name = movieBriefIntro.getName();
        this.category = movieBriefIntro.getCategory();
        this.releaseTime = releaseTime;
        this.playAmount = movieBriefIntro.getPlayAmount();
        this.score = movieBriefIntro.getScore();
    }
}
