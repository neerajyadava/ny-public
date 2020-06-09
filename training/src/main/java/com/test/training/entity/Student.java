package com.test.training.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Student {
	private String studentName;
	private String studentDOB;
	private String studentId;
	private Set<Course> courses = new HashSet<>();

}