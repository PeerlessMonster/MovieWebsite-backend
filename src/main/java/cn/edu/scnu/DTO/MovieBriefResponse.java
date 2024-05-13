package cn.edu.scnu.DTO;

import java.sql.Timestamp;

public interface MovieBriefResponse {
    Integer getId();
    String getName();
    String getCategory();
    Timestamp getReleaseTime();
    int getPlayAmount();
    float getScore();
}
