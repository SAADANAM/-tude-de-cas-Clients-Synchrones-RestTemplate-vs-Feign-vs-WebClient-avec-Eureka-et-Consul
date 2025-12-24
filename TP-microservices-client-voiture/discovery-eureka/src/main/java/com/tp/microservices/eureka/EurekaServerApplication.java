package com.tp.microservices.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] arguments) {
        // Démarre l'application Spring Boot
        SpringApplication.run(EurekaServerApplication.class, arguments);
    }
}
