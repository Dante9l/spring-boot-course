package top.zby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class scheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(scheduleApplication.class, args);
    }
}
