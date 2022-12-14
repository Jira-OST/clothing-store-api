package com.example.clothingstoreapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClothingStoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingStoreApiApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;

    }


}
