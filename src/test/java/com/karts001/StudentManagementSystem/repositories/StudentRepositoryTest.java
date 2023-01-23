package com.karts001.StudentManagementSystem.repositories;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.karts001.StudentManagementSystem.dto.SubjectRequestResponse;
import com.karts001.StudentManagementSystem.models.Student;
import com.karts001.StudentManagementSystem.models.Subjects;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest1;
    @Autowired
    private SubjectsRepository underTest2;

    @AfterEach
    void tearDown() {
        underTest1.deleteAll();

    }

    @Test
    void testThatIfAnEmailWhichAlreadyExistsIsQueriedAStudentObjectIsReturnedWithTheRelevantStudentInformation() {
        // Arrange

        System.out.println(underTest1.findAll());
        String email = "shiva@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            email,
            "Male",
            Arrays.asList(new Subjects(1L, "Maths")) 
        );
         underTest1.save(student);


        // Act
        Optional<Student> studentFromDatabase = underTest1.findStudentByEmail(email);
        System.out.println(studentFromDatabase.get().getName());

        // Assert
        Assertions.assertThat(studentFromDatabase).isNotEmpty();
        Assertions.assertThat(Optional.of(studentFromDatabase.get().getEmail())).hasValue(email);
    }

    @Test
    void testThatIfAnEmailWhichDoesNotExistIsQueriedSomethingHappens() {
        // Arrange

        String email = "email_which_does_not_exist@gmail.com";

        // Act
        Optional<Student> studentFromDatabase = underTest1.findStudentByEmail(email);

        // Assert
        Assertions.assertThat(studentFromDatabase).isEmpty();
        
    }

    @Test
    void testThatSubjectDataIsFoundInSubjectRepository() {
        // Arrange

        System.out.println(underTest1.findAll());
        String email = "shiva@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            email,
            "Male",
            Arrays.asList(new Subjects(1L, "Maths")) 
        );
         underTest1.save(student);


        // Act
        List<SubjectRequestResponse> studentSubjectFromDatabase = underTest2.getStudentSubjects(1L);
        System.out.println(studentSubjectFromDatabase);
        // Assert
        Assertions.assertThat(studentSubjectFromDatabase).isNotEmpty();
        SubjectRequestResponse subjectList = studentSubjectFromDatabase.get(0);
        Assertions.assertThat(subjectList.getName()).isEqualTo("Shiva");
        Assertions.assertThat(subjectList.getClassName()).isEqualTo("Maths");
    }


    @Test
    void testThatSubjectDataIsNotFoundWhenNoSubjectDataIsProvided() {
        // Arrange

        System.out.println(underTest1.findAll());
        String email = "shiva@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            email,
            "Male",
            Arrays.asList(new Subjects(1L, null)) 
        );
         underTest1.save(student);


        // Act
        List<SubjectRequestResponse> studentSubjectFromDatabase = underTest2.getStudentSubjects(1L);
        System.out.println(studentSubjectFromDatabase);
        // Assert
        Assertions.assertThat(studentSubjectFromDatabase).isNotEmpty();;
        SubjectRequestResponse subjectList = studentSubjectFromDatabase.get(0);
        Assertions.assertThat(subjectList.getName()).isEqualTo("Shiva");
        Assertions.assertThat(subjectList.getClassName()).isEqualTo(null);

    }
}
