package cn.edu.scnu.config;

import org.springframework.session.MapSession;

public class SessionConfig {
    public static final int EXPIRE = MapSession.DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
    /* 1800 seconds = 30 minutes */
    public static final int EXPIRE_REMEMBER = 604800;
    /* 604800 seconds = 7 days */
}
