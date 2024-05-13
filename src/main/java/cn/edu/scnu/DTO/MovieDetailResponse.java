package cn.edu.scnu.DTO;

public interface MovieDetailResponse extends MovieBriefResponse {
    String getRegion();
    String getDirector();
    String getScriptwriter();
    String getActor();
    int getDuration();
    String getDescription();
    int getVip();
}
