package com.sergio.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.springdemo.rest.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> students;

	// define @PostConstruct to load student data
	@PostConstruct
	public void loadData() {
		students = new ArrayList<Student>();
		students.add(new Student("Sergio", "Oliveira"));
		students.add(new Student("Clotilde", "Coelho"));
		students.add(new Student("Félix", "Francisco"));
	}

	// define endpoint for "/students" - return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	// define endpoint for "/students/{studentId}" - return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		return students.get(studentId);
	}
}
