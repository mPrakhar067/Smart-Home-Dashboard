package com.smarthome.platform.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartHomeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeManagementApplication.class, args);
    }

}
