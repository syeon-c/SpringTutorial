package com.test1.b1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class B1Application {

    public static void main(String[] args) {
        SpringApplication.run(B1Application.class, args);
    }

}
