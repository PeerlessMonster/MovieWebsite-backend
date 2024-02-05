package cn.edu.scnu;

import cn.edu.scnu.config.SessionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = SessionConfig.EXPIRE)
public class MovieWebsiteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieWebsiteBackendApplication.class, args);
	}

}
