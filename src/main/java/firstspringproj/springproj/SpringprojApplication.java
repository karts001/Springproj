package firstspringproj.springproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import firstspringproj.springproj.student.StudentController;

@SpringBootApplication
@ComponentScan(basePackageClasses = StudentController.class)
public class SpringprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringprojApplication.class, args);
	}
} 
