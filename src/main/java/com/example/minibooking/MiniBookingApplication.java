package com.example.minibooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MiniBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniBookingApplication.class, args);
    }

}
