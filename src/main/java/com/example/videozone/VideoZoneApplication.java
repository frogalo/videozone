package com.example.videozone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.videozone.repository")
public class VideoZoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoZoneApplication.class, args);
    }
}
