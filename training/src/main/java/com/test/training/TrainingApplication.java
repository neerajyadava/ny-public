package com.test.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {
"com.test" })
@EnableScheduling
@EnableConfigurationProperties
public class TrainingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}
}
