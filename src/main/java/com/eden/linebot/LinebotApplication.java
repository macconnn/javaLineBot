package com.eden.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LinebotApplication {
    public static void main(String[] args) {
        SpringApplication.run(LinebotApplication.class, args);
    }

}
