package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		Student marriam =	new Student("Marriam", "marriam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
		Student alex =	new Student( "Alex", "alex.toast@gmail.com", LocalDate.of(2001, Month.JANUARY, 5));
		return args ->{
			studentRepository.saveAll(List.of(alex,marriam));
		};
		
		
	}
}
