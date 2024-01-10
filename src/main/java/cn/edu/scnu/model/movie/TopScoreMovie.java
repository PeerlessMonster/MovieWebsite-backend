package cn.edu.scnu.model.movie;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("TopScoreMovieView")
public class TopScoreMovie implements Serializable {
    private static final long serialVersionUID = 7397482145827445341L;

    @TableId()
    private Integer id;
    private String name;
    private float score;
}
