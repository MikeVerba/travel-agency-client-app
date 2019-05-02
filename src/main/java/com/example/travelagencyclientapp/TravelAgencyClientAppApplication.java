package com.example.travelagencyclientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.travelagencyclientapp"})
public class TravelAgencyClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyClientAppApplication.class, args);
	}

}
