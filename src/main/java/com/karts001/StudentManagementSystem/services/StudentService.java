package com.karts001.StudentManagementSystem.services;

import java.util.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.karts001.StudentManagementSystem.models.Student;
import com.karts001.StudentManagementSystem.repositories.StudentRepository;


@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		
		return studentRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
		
	}

    public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		boolean studentEmailAlreadyExists = studentOptional.isPresent();
		 
		if (studentEmailAlreadyExists){
			throw new IllegalStateException("Email already being used");
		};

		studentRepository.save(student);
    }

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("Student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}

    public void updateStudent(Long studentId,
	String name,
	String email) {

		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
			"Student with ID " + studentId + " does not exist" 
		));
		
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}

		studentRepository.save(student);
    }

}
