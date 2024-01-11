package cn.edu.scnu.model.movie;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDetailResponse implements Serializable {
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

    public MovieDetailResponse(Integer id, String name, String category, String region, String director, String scriptwriter, String actor, String releaseTime, int duration, String description, int playAmount, float score, int vip) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.region = region;
        this.director = director;
        this.scriptwriter = scriptwriter;
        this.actor = actor;
        this.releaseTime = releaseTime;
        this.duration = duration;
        this.description = description;
        this.playAmount = playAmount;
        this.score = score;
        this.vip = vip;
    }
}
