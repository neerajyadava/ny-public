package com.test.training;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.test.training.api.TrainingController;
import com.test.training.service.CourseService;
import com.test.training.service.RegistrationService;
import com.test.training.service.StudentService;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class TrainingConfiguration {

	@Bean
	public StudentService studentService() {
		return new StudentService();
	}
	
	@Bean
	public CourseService courseService() {
		return new CourseService();
	}
	
	
	@Bean
	public RegistrationService registrationService(final StudentService studentService,
			final CourseService courseService) {
		return new RegistrationService(studentService, courseService);
	}
	
	@Bean
	@DependsOn({"studentService", "courseService", "registrationService" })
	public TrainingController trainingController(final StudentService studentService,
			final CourseService courseService, 
			final RegistrationService registrationService) {
		return new TrainingController(studentService, courseService, registrationService);
	}
}
