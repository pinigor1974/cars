package com.stady.cars;

import com.stady.cars.configuration.MainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MainConfiguration.class})
public class CarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}

}
