package com.test.training.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.training.entity.Course;
import com.test.training.entity.Student;

public final class TrainingUtils {
	
	static final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.INDENT_OUTPUT);
	
	private static List<Student> students = new ArrayList<Student>(); 
	private static List<Course> courses = new ArrayList<Course>(); 
	
	private static final String USER_DIR = System.getProperty("user.dir");
	private static final File STUDENT_FILE = FileUtils.getFile(USER_DIR + "/students.json");
	private static final File COURSE_FILE = FileUtils.getFile(USER_DIR + "/courses.json");
	
	
	static {
		try {
			JavaType studentType = mapper.getTypeFactory().constructCollectionType(List.class, Student.class);
			students = mapper.readValue(STUDENT_FILE, studentType);
			
			JavaType courseType = mapper.getTypeFactory().constructCollectionType(List.class, Course.class);
			courses = mapper.readValue(COURSE_FILE, courseType);
		} catch (IOException e) {
			System.out.println("Error reading source");
		}
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		students = mapper.readValue(STUDENT_FILE, 
				new TypeReference<List<Student>>(){});
		System.out.println(students);
	}
	
	
	public static List<Student> getStudents()  {
		return students;
	}

	public static List<Course> getCourses()  {
		return courses;
	}

	public static void updateStudents(List<Student> _students)  {
	    students = _students;
		try {
			mapper.writeValue(STUDENT_FILE, students);
		} catch (IOException e) {
			System.out.println("Error writing source");
		}
	}

	public static void updateCourses(List<Course> _course) {
		courses = _course;
		try {
			mapper.writeValue(COURSE_FILE, courses);
		} catch (IOException e) {
			System.out.println("Error writing source");
		}
	}
}
