package com.karts001.StudentManagementSystem.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.karts001.StudentManagementSystem.models.Student;
import com.karts001.StudentManagementSystem.services.StudentService;
import com.karts001.StudentManagementSystem.services.SubjectsService;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    
    private final StudentService studentService;
    private final SubjectsService subjectsService;
    

    public StudentController(StudentService studentService, SubjectsService subjectsService) {
        this.studentService = studentService;
        this.subjectsService = subjectsService;
    }

    @GetMapping
	public List<Student> getStudents() {
        
        return studentService.getStudents();
	}

    @PostMapping
    public void registerNewStudent (@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "deletestudent/{studentId}")
    public void deleteStudent (@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "updatestudent/{studentId}")
    public void updateStudent (@PathVariable("studentId") Long studentId,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @GetMapping(path = "getStudentSubjects/{studentId}")
    public void getStudentSubjects (@PathVariable("studentId") Long studentId) {
        subjectsService.getStudentSubjects(studentId);
    }
}
