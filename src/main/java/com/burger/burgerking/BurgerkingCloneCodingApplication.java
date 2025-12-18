package com.burger.burgerking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BurgerkingCloneCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerkingCloneCodingApplication.class, args);
    }

}
