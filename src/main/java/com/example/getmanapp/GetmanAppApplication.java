package com.example.getmanapp;

import ch.qos.logback.core.util.ContentTypeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

@SpringBootApplication
public class GetmanAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetmanAppApplication.class, args);
    }

}
