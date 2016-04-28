package com.globant.glow.staffing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Controller
@EnableZuulProxy
@CrossOrigin
public class ZuulApplication {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);

        SpringApplication springApplication =
        		new SpringApplication(ZuulApplication.class);
        		springApplication.addListeners(
        		new ApplicationPidFileWriter("ZuulApplication.pid"));
        		springApplication.setWebEnvironment(true);
        		springApplication.run(args);
    }
}
