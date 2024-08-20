package com.client.QuizApp;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.client.QuizApp.Students.Student;

import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class QuizApp {
	public static void main(String[] args) {
		SpringApplication.run(QuizApp.class, args);
	}
	
	@GetMapping
	public List<Student> hello() {
		final Student students = new Student(
			938741, 	
			"Felix Okwuosa", 
			"felix.okwuosa@gmail.com", 
			LocalDate.of(2004,Month.JUNE,17),
			19
		);

		return List.of(students);

		// return List.of(
		// 	new Student(
		// 		123124,
		// 		"Felix Okuwosa",
		// 		"okwosafelix634@gmail.com",
		// 		LocalDate.of(2004, Month.JUNE, 17),
		// 		19
		// 	)
		// );
	}
	

}