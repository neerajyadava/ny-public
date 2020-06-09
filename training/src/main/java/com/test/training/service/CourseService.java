package com.test.training.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.test.training.entity.Course;
import com.test.training.entity.GenericContainer;
import com.test.training.entity.Student;
import com.test.training.util.TrainingUtils;

public class CourseService {
	
	public GenericContainer<Course> addCourse(final String courseName) {
		if (StringUtils.isEmpty(courseName)) {
			return GenericContainer.of("Please provide Course Name");
		}
		List<Course> courselist = TrainingUtils.getCourses();
		Optional<Course> optCourse = courselist.stream()
				.filter(course -> courseName.equalsIgnoreCase(course.getCourseName())).findFirst();
		if (optCourse.isPresent()) {
			return GenericContainer.of("Course information already exists.");
		}
		Course course = new Course();
		course.setCourseId("COURSE_" + UUID.randomUUID());
		course.setCourseName(courseName);
		courselist.add(course);
		TrainingUtils.updateCourses(courselist);
		return GenericContainer.of(course);
	}

	public GenericContainer<List<Course>> removeCourse(final String courseName) {
		if(StringUtils.isEmpty(courseName)) {
			return GenericContainer.of("Please provide Valid Course name");
		}
		List<Course> courselist = TrainingUtils.getCourses();
		Optional<Course> optCourse = courselist.stream()
				.filter(course -> 
				courseName.equalsIgnoreCase(course.getCourseName())).findFirst();
		if (!optCourse.isPresent()) {
			return GenericContainer.of("Please provide Valid Course name");
		}
		courselist.remove(optCourse.get());
		
		
		List<Student> studentslist = TrainingUtils.getStudents();
		studentslist.stream().filter(student -> 
		student.getCourses().contains(optCourse.get())).collect(Collectors.toList()).
		forEach(std -> std.getCourses().remove(optCourse.get()));
		TrainingUtils.updateStudents(studentslist);
		TrainingUtils.updateCourses(courselist);
		return GenericContainer.of(courselist);
	}


	public List<Course> getAllCourses() {
		return TrainingUtils.getCourses();
	}

}
