package firstspringproj.springproj.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testThatIfAnEmailWhichAlreadyExistsIsQueriedAStudentObjectIsReturnedWithTheRelevantStudentInformation() {
        // Arrange

        System.out.println(underTest.findAll());
        String email = "shiva@gmail.com";
        Student student = new Student(
            "Shiva",
            LocalDate.of(1996, Month.MARCH, 24),
            email,
            "Male",
            Arrays.asList(new String[] {"Maths", "Physics", "Chemistry"}) 
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

        // Act
        Optional<Student> studentFromDatabase = underTest.findStudentByEmail(email);

        // Assert
        Assertions.assertThat(studentFromDatabase).isEmpty();
        
    }
}
