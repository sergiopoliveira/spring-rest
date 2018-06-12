package com.sergio.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

		// check the studentId against list size

		if ((studentId >= students.size() || studentId < 0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		return students.get(studentId);

	}

	// Add an exception handler using @ExceptionHandler
	// within <> we have the Type of the Response Body, and in the method
	// argument we have the Exception type to handle / catch
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

		// create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// add another exception handler ... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

		// create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
