package firstspringproj.springproj.student;

import org.mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
        
    }

    @Test
    void testGetStudentsMethodInvokesTheFindAllMethodWithTheStudentRepository() {
        // Act
        underTest.getStudents();

        // Assert
        verify(studentRepository).findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Test
    void testAStudentIsAddedToTheRepository() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        // Act
        underTest.addNewStudent(student);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        // Assert
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        Assertions.assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void testThrowsIllegalStateExceptionWhenAStudentUsingAnEmailWhichAlreadyExistsInTheStudentRepository () {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        // Act
        BDDMockito.given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(new Student()));

        // Assert
        Assertions.assertThatThrownBy(()->underTest.addNewStudent(student))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Email already being used");

        verify(studentRepository, Mockito.never()).save(any());
    }

    @Test
    void testThrowsIllegalStateExceptionWhenTheDeleteByIdMethodIsUsedOnAStudentWhosIdIsNotInTheDatabase() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );
        

        // Act
        BDDMockito.given(studentRepository.existsById(student.getId())).willReturn(false);
        

        // Assert
        Assertions.assertThatThrownBy(()->underTest.deleteStudent(student.getId()))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Student with id " + student.getId() + " does not exist");

        verify(studentRepository, Mockito.never()).deleteById(any());
        
    }

    @Test
    void testTheDeleteStudentMethodInvokesTheDeleteByIdMethod() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );
        

        // Act
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        underTest.deleteStudent(student.getId());

        // Assert
        verify(studentRepository).deleteById(any());
    }    

    @Test
    void testWhenAValidIdIsSuppliedAndANewNameIsProvidedTheStudentNameIsUpdated() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        String newUserName = "Test Name";
        String newUserEmail = null;
        
        // Act
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        underTest.updateStudent(student.getId(), newUserName, newUserEmail);
        
        // Assert
        verify(studentRepository).findById(student.getId());
        verify(studentRepository).save(student);
        Assertions.assertThat(student.getName()).isEqualTo(newUserName);

    }

    @Test
    void testWhenAValidIdIsSuppliedAndANewEmailIsProvidedTheStudentEmailIsUpdated() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        String newStudentName = null;
        String newStudentEmail = "test@gmail.com";
        
        // Act
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        underTest.updateStudent(student.getId(), newStudentName, newStudentEmail);
        
        // Assert
        verify(studentRepository).findById(student.getId());
        verify(studentRepository).save(student);
        Assertions.assertThat(student.getEmail()).isEqualTo(newStudentEmail);

    }

    @Test
    void testWhenAValidIdIsSuppliedButUpdatedValuesAreTheSameAsTheOriginalValues() {
        // Arrange
        String originalName = "Shiva";
        String originalEmail = "shiva@gmail.com";

        Student student = new Student(
            originalName,
            LocalDate.of(1996, Month.MARCH, 24),
            originalEmail,
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        String newUserName = originalName;
        String newUserEmail = originalEmail;
        
        // Act  
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        underTest.updateStudent(student.getId(), newUserName, newUserEmail);
        
        // Assert
        verify(studentRepository).findById(student.getId());
        verify(studentRepository).save(student);
        Assertions.assertThat(student.getName()).isEqualTo(originalName);

    }

    @Test
    void testWhenAValidIdIsSuppliedAndANewEmailAndNameIsProvidedTheStudentEmailAndNameAreUpdated() {
        // Arrange
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com",
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );

        String newStudentName = "test name";
        String newStudentEmail = "test@gmail.com";
        
        // Act
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        underTest.updateStudent(student.getId(), newStudentName, newStudentEmail);
        
        // Assert
        verify(studentRepository).findById(student.getId());
        verify(studentRepository).save(student);
        Assertions.assertThat(student.getEmail()).isEqualTo(newStudentEmail);
        Assertions.assertThat(student.getName()).isEqualTo(newStudentName);

    }

    @Test
    void testThrowsAnIllegalStateExceptionWhenAnInvalidIDIsSupplied() {
        // Arrange
        String originalName = "Shiva";
        String originalEmail = "shiva@gmail.com";

        Student student = new Student(
            originalName,
            LocalDate.of(1996, Month.MARCH, 24),
            originalEmail,
            "Male",
            new String[] {"Maths", "Physics", "Chemistry"}
        );
        student.setId(1L);

        String newName = originalName;
        String newEmail = originalEmail;
        
        
        // Act
        BDDMockito.given(studentRepository.findById(student.getId())).willReturn(Optional.ofNullable(null));
        
        // Assert
        Assertions.assertThatThrownBy(()->underTest.updateStudent(student.getId(), newName, newEmail))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Student with ID " + student.getId() + " does not exist");

        verify(studentRepository, Mockito.never()).save(any());

    }
}
