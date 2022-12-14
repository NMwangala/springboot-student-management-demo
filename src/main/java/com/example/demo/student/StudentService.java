package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	public List<Student> getStudent() {
		
		return studentRepository.findAll();
		
	}
	
	public void saveStudent(Student student) {
	Optional<Student> studentOptional=	studentRepository.findByEmail(student.getEmail());
	if(studentOptional.isPresent()) {
		throw new IllegalStateException("Email taken");
	}
		studentRepository.save(student);
	}
	
	public void deleteStudent(Long studentId) {
	boolean exists =	studentRepository.existsById(studentId);
	if(!exists) {
		throw new IllegalStateException("Student with ID " + studentId + " does not exist");
	}
		studentRepository.deleteById(studentId);
	}
	@Transactional
	public void updateStudent(Long studentId,String name,String email) {
		
		Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("Student not found")
			
		);
	
		if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)) {
			student.setName(name);
		}
		if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
		Optional<Student> studentOptional=	studentRepository.findByEmail(email);
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		student.setEmail(email);
		}
		//student.setEmail(email);
		//studentRepository.save(student);
		
	}
}
