package com.test.training.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.test.training.entity.Course;
import com.test.training.entity.GenericContainer;
import com.test.training.entity.Student;
import com.test.training.util.TrainingUtils;

public class StudentService {

	public GenericContainer<Student> addStudent(final String studentName, final String dob) {
		if(StringUtils.isEmpty(studentName) || StringUtils.isEmpty(dob)) {
			return GenericContainer.of("Please provide Student Name and Date Of Birth.");
		}
		List<Student> studentslist = TrainingUtils.getStudents();
		Optional<Student> optStudent = studentslist.stream()
				.filter(student -> studentName.equalsIgnoreCase(student.getStudentName())
						&& 	dob.equalsIgnoreCase(student.getStudentDOB())).findFirst();
		if (optStudent.isPresent()) {
			return GenericContainer.of("Student information already exist.");
		}
		Student student = new Student();
		student.setStudentId("ST_" + UUID.randomUUID());
		student.setStudentName(studentName);
		student.setStudentDOB(dob); 
		studentslist.add(student);
		TrainingUtils.updateStudents(studentslist);
		return GenericContainer.of(student);
	}
	
	public GenericContainer<List<Student>> removeStudent(final String id) {
		if(StringUtils.isEmpty(id)) {
			return GenericContainer.of("Please provide Valid Student Id");
		}
		List<Student> studentslist = TrainingUtils.getStudents();
		Optional<Student> optStudent = studentslist.stream()
				.filter(student -> 
				 id.equalsIgnoreCase(student.getStudentId())).findFirst();
		if (!optStudent.isPresent()) {
			return GenericContainer.of("Please provide Valid Student Id");
		}
		studentslist.remove(optStudent.get());
		TrainingUtils.updateStudents(studentslist);
		return GenericContainer.of(studentslist);
	}
	
	
	public List<Student> getAllStudents() {
		return TrainingUtils.getStudents();
	}
	
	
	public GenericContainer<Student> registerStudent(final String studentId, final String courseName) {
		List<Student> studentslist = TrainingUtils.getStudents();
		Optional<Student> optStudent = studentslist.stream()
				.filter(student -> 
				studentId.equalsIgnoreCase(student.getStudentId())).findFirst();
		if (!optStudent.isPresent()) {
			return GenericContainer.of("Please provide Valid Student Id");
		}
		List<Course> courselist = TrainingUtils.getCourses();
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
		return GenericContainer.of(optStudent.get());
	}
	
}
