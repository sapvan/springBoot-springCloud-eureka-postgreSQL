package com.globant.glow.staffing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class GloberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GloberServiceApplication.class, args);
    }
}
