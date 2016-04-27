package com.globant.glow.staffing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {

        //SpringApplication.run(UserServiceApplication.class, args);


        SpringApplication springApplication =
        		new SpringApplication(UserServiceApplication.class);
        		springApplication.addListeners(
        		new ApplicationPidFileWriter("UserServiceApplication.pid"));
        		springApplication.run(args);
    }
}
