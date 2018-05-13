package com.sergio.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.springdemo.rest.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	// define endpoint for "/students" - return list of students
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Sergio", "Oliveira"));
		students.add(new Student("Clotilde","Coelho"));
		students.add(new Student("Félix","Francisco"));
		
		return students;
	}
}
