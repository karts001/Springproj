package firstspringproj.springproj;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import firstspringproj.student.Student;

@SpringBootApplication
@RestController
public class SpringprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringprojApplication.class, args);
	}

	@GetMapping("/")
	public List<Student> hello() {
		return List.of(
			new Student(
				1L,
				"Shiva",
				LocalDate.of(1996, Month.MARCH, 24),
				"shiva@gmail.com",
				27
			)
		);
	}
}
