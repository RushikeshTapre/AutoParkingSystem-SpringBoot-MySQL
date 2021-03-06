package com.app.myapp;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoParkingSystemApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(AutoParkingSystemApplication.class, args);
	}

}
