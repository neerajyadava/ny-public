package com.test.training.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.test.training.entity.Course;
import com.test.training.entity.GenericContainer;
import com.test.training.entity.Student;
import com.test.training.util.TrainingUtils;

public class RegistrationService {

	final StudentService studentService; 
	final CourseService courseService;

	public RegistrationService(final StudentService studentService, 
			final CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	public GenericContainer<Student> registerStudent(final String studentId, final String courseName) {
		List<Student> studentslist = studentService.getAllStudents();
		Optional<Student> optStudent = studentslist.stream()
				.filter(student -> 
				studentId.equalsIgnoreCase(student.getStudentId())).findFirst();
		if (!optStudent.isPresent()) {
			return GenericContainer.of("Please provide Valid Student Id");
		}
		List<Course> courselist = courseService.getAllCourses();
		Optional<Course> optCourse = courselist.stream()
				.filter(course -> 
				courseName.equalsIgnoreCase(course.getCourseName())).findFirst();
		if (!optCourse.isPresent()) {
			return GenericContainer.of("Please provide Valid Course name");
		}

		if(optStudent.get().getCourses().contains(optCourse.get())) {
			return GenericContainer.of("Student already registered in Course");
		}
		optStudent.get().getCourses().add(optCourse.get());
		TrainingUtils.updateStudents(studentslist);
		return GenericContainer.of(optStudent.get());
	}


	public GenericContainer<List<Student>> getCourseWiseStudents(final String courseName) {
		List<Student> studentslist = studentService.getAllStudents();
		List<Course> courselist = courseService.getAllCourses();
		Optional<Course> optCourse = courselist.stream().filter
				(course -> courseName.equalsIgnoreCase(course.getCourseName())).findFirst();

		if(optCourse.isPresent()) {
			List<Student> studentSublist =		
					studentslist.stream().filter(student -> 
					student.getCourses().contains(optCourse.get())).
					sorted(Comparator.comparing(Student::getStudentName)).collect(Collectors.toList());
			studentSublist.forEach(std -> std.getCourses().removeIf(c -> !c.getCourseName().equalsIgnoreCase(courseName)));
			return GenericContainer.of(studentSublist);

		}else {
			return GenericContainer.of("Please provide Valid Course");
		}
	}

}
