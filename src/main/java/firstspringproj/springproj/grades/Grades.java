package firstspringproj.springproj.grades;

import jakarta.persistence.*;

@Entity
public class Grades {
    @Id
    private Long studentId;
    private String subject;
    private Integer averageGrade;
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Integer getAverageGrade() {
        return averageGrade;
    }
    public void setAverageGrade(Integer averageGrade) {
        this.averageGrade = averageGrade;
    }
    
}
