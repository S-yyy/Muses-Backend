package com.mu.muses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EntityScan(basePackages = "com.mu.muses.entity")
@EnableJpaRepositories(basePackages={"com.mu.muses.dao"})
public class MusesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusesApplication.class, args);
    }

}
