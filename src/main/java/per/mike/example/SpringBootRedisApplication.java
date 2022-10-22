package per.mike.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);

        System.out.println("##############################");
        System.out.println("##                          ##");
        System.out.println("##  REDIS SPRING BOOT START ##");
        System.out.println("##                          ##");
        System.out.println("##############################");
	}

}
