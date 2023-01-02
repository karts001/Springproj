package firstspringproj.springproj.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		
		return studentRepository.findAll();
		
	}

    public void addNewStudent(Student student) {
		Optional<Student> StudentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (StudentOptional.isPresent()){
			throw new IllegalStateException("Email already being used");
		};

		studentRepository.save(student);
    }

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("Student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	
}
