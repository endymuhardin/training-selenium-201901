package com.muhardin.endy.belajar.selenium;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class Halo {

    public static void main(String[] args) {
        System.out.println("Halo");
        SpringApplication.run(Halo.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    
    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}
