package firstspringproj.springproj.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void testThatIfAnEmailWhichAlreadyExistsIsQueriedAStudentObjectIsReturnedWithTheRelevantStudentInformation() {
        // Arrange

        String email = "shiva@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            email
        );
        underTest.save(student);


        // Act
        Optional<Student> studentFromDatabase = underTest.findStudentByEmail(email);
        System.out.println(studentFromDatabase.get().getName());

        // Assert
        Assertions.assertThat(studentFromDatabase).isNotEmpty();
        Assertions.assertThat(Optional.of(studentFromDatabase.get().getEmail())).hasValue(email);
    }

    @Test
    void testThatIfAnEmailWhichDoesNotExistIsQueriedSomethingHappens() {
        // Arrange

        String email = "email_which_does_not_exist@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            "shiva@gmail.com"
        );
        underTest.save(student);

        // Act
        Optional<Student> studentFromDatabase = underTest.findStudentByEmail(email);
        System.out.println(studentFromDatabase);

        // Assert
        Assertions.assertThat(studentFromDatabase).isEmpty();
        // Assertions.assertThat(Optional.of(studentFromDatabase.get().getEmail())).hasValue(email);
    }
}
