package com.karts001.StudentManagementSystem.models;

import java.time.LocalDate;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @Nullable
    @OneToMany(targetEntity = Subjects.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ss_fk", referencedColumnName = "id")
    private List<Subjects> subjects;

    public Student(String name, LocalDate dob, String email, String gender, List<Subjects> subjects) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.subjects = subjects;
    }

}

