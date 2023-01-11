package firstspringproj.springproj.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {
            Student shiva = new Student(
				"Shiva",
				LocalDate.of(1996, Month.MARCH, 24),
				"shiva@gmail.com"
			);

            Student tabs = new Student(
				"Tabs",
				LocalDate.of(1997, Month.JUNE, 25),
				"tabs@gmail.com"
			);

        repository.saveAll(List.of(shiva, tabs));

        };

    };
}
