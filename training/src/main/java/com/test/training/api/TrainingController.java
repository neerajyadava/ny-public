package com.test.training.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.training.entity.Course;
import com.test.training.entity.GenericContainer;
import com.test.training.entity.Student;
import com.test.training.service.CourseService;
import com.test.training.service.RegistrationService;
import com.test.training.service.StudentService;

@RestController
@RequestMapping("/studentservice/api/")
@SuppressWarnings("rawtypes")
public class TrainingController {
	
	StudentService studentService;
	
	CourseService courseService;
	
	RegistrationService registrationService;
	

	public TrainingController(StudentService studentService, CourseService courseService,
			RegistrationService registrationService) {
		super();
		this.studentService = studentService;
		this.courseService = courseService;
		this.registrationService = registrationService;
	}

	@PatchMapping("/addStudent/{studentname}/{dob}")
	public ResponseEntity addStudent(@PathVariable final String studentname, @PathVariable final String dob) {
		GenericContainer<Student> genericContainer = studentService.addStudent(studentname, dob);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericContainer.getError());
		else
			return ResponseEntity.ok(genericContainer.getResponse());
	}

	@GetMapping("/getAllStudents")
	public ResponseEntity getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@DeleteMapping("/removeStudent/{studentId}")
	public ResponseEntity<String> removeStudent(@PathVariable final String studentId) {
		GenericContainer<List<Student>> genericContainer = studentService.removeStudent(studentId);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		else
			return new ResponseEntity<>(studentId, HttpStatus.OK);
	}

	@PatchMapping("/addCourse/{courseName}")
	public ResponseEntity addCourse(@PathVariable final String courseName) {
		GenericContainer<Course> genericContainer = courseService.addCourse(courseName);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericContainer.getError());
		else
			return ResponseEntity.ok(genericContainer.getResponse());
	}

	@GetMapping("/getAllCourses")
	public ResponseEntity getAllCourses() {
		return ResponseEntity.ok(courseService.getAllCourses());
	}

	@DeleteMapping("/removeCourse/{courseName}")
	public ResponseEntity<String> removeCourse(@PathVariable final String courseName) {
		GenericContainer<List<Course>> genericContainer = courseService.removeCourse(courseName);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		else
			return new ResponseEntity<>(courseName, HttpStatus.OK);
	}

	
	@PatchMapping("/register/{studentId}/{courseName}")
	public ResponseEntity registerStudent(@PathVariable final String studentId, 
			@PathVariable final String courseName) {
		GenericContainer<Student> genericContainer = registrationService.registerStudent(studentId, courseName);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericContainer.getError());
		else
			return ResponseEntity.ok(genericContainer.getResponse());

	}
	
	@GetMapping("/getCourseWiseStudents/{courseName}")
	public ResponseEntity getCourseWiseStudents(@PathVariable final String courseName) {
		GenericContainer<List<Student>> genericContainer = registrationService.getCourseWiseStudents(courseName);
		if (!StringUtils.isEmpty(genericContainer.getError()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericContainer.getError());
		else
			return ResponseEntity.ok(genericContainer.getResponse());

		
	}
	
}
