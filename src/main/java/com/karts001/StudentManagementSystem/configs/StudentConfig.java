package com.karts001.StudentManagementSystem.configs;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.karts001.StudentManagementSystem.models.Student;
import com.karts001.StudentManagementSystem.models.Subjects;
import com.karts001.StudentManagementSystem.repositories.StudentRepository;


@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {
            Student shiva = new Student(
				"Shiva",
				LocalDate.of(1996, Month.MARCH, 24),
				"shiva@gmail.com",
                "Male",
                Arrays.asList(new Subjects(1L, "Maths")) 
                
			);

            Student tabs = new Student(
				"Tabs",
				LocalDate.of(1997, Month.JUNE, 25),
				"tabs@gmail.com",
                "Female",
                Arrays.asList(new Subjects(2L, "Physics"))
                
			);

        repository.saveAll(List.of(shiva, tabs));

        };

    };
}
