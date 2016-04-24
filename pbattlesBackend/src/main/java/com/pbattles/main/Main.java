package com.pbattles.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created with IntelliJ IDEA.
 * User: noflaxe
 * Date: 23.04.16
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */



@SpringBootApplication
@Configuration
@ImportResource(value = "classpath:spring-config.xml")
public class Main  extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
