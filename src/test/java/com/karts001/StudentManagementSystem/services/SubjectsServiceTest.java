package com.karts001.StudentManagementSystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

import com.karts001.StudentManagementSystem.repositories.SubjectsRepository;

@ExtendWith(MockitoExtension.class)
public class SubjectsServiceTest {
    @Mock
    private SubjectsRepository subjectsRepository;
    private SubjectsService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SubjectsService(subjectsRepository);
    }

    @Test
    void testTheGetStudentsMethodInvokesTheJoinStudentNameAndSubjectMethod() {
        // Arrange
        Long studentId = 1L;

        // Act
        underTest.getStudentSubjects(studentId);

        // Assert
         verify(subjectsRepository).joinStudentNameAndSubjects(1L);

    }
}
