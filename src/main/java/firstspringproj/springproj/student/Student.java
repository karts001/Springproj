package firstspringproj.springproj.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import firstspringproj.springproj.grades.Grades;
import jakarta.persistence.*;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dob;
    private String email;
    @Transient
    private Integer age;
    private String gender;
    // @OneToMany(targetEntity = Grades.class, cascade = CascadeType.ALL)
    // @JoinColumn(name="sg_fk",referencedColumnName = "id")
    private List<String> subjects;


    public Student(String name, LocalDate dob, String email, String gender, List<String> subjects) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.subjects = subjects;
    }

    public Student(Long id, String name, LocalDate dob, String email, String gender, List<String> subjects) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.subjects = subjects;
    }

    public Student () {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", age=" + age
                + ", gender=" + gender + "]";
    }
}

