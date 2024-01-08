package cn.edu.scnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MovieWebsiteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieWebsiteBackendApplication.class, args);
	}

}
