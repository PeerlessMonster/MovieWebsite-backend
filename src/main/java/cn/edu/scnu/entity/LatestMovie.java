package cn.edu.scnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("LatestMovieView")
public class LatestMovie implements Serializable {
    private static final long serialVersionUID = -4179950950079037337L;

    @TableId
    private Integer id;
    private String name;
    private float score;
}
