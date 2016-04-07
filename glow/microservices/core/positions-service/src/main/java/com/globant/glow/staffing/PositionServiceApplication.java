package com.globant.glow.staffing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@EnableDiscoveryClient
//@ImportResource("classpath:appContext.xml")
public class PositionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PositionServiceApplication.class, args);
    }
}
