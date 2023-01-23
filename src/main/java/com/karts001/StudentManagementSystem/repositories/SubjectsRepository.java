package com.karts001.StudentManagementSystem.repositories;

import com.karts001.StudentManagementSystem.dto.SubjectRequestResponse;
import com.karts001.StudentManagementSystem.models.Subjects;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    @Query("SELECT new com.karts001.StudentManagementSystem.dto.SubjectRequestResponse(s.name, c.className) FROM Student s JOIN s.subjects c")
    List<SubjectRequestResponse> getStudentSubjects(Long id);
    
}
