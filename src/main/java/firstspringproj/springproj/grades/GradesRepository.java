package firstspringproj.springproj.grades;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Grades> findStudentGrades(String Email); 
}
