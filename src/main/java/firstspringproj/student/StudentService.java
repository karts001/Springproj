package firstspringproj.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class StudentService {
	public List<Student> getStudents() {
		return List.of(
			new Student(
				1L,
				"Shiva",
				LocalDate.of(1996, Month.MARCH, 24),
				"shiva@gmail.com",
				26
			)
		);
	}
}
