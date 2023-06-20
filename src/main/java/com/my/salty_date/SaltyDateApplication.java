package com.my.salty_date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SaltyDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaltyDateApplication.class, args);
    }

}
