package com.karts001.StudentManagementSystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karts001.StudentManagementSystem.dto.SubjectRequestResponse;
import com.karts001.StudentManagementSystem.repositories.SubjectsRepository;

@Service
public class SubjectsService {
    private final SubjectsRepository subjectsRepository;

    public SubjectsService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    public List<SubjectRequestResponse> getStudentSubjects(Long id) {
        System.out.println(subjectsRepository.getStudentSubjects(id));
        return subjectsRepository.getStudentSubjects(id);
    }
}
