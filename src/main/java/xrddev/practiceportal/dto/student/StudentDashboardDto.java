package xrddev.practiceportal.dto.student;

import lombok.Data;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.student.Student;

import java.util.List;

@Data
public class StudentDashboardDto {

    private long id;
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Department department;
    private int yearOfStudy;
    private Double averageGrade;
    private String preferredLocation;
    private List<Skills> skills;
    private List<Interests> interests;

    public StudentDashboardDto(Student student) {
        this.id = student.getId();
        this.studentNumber = student.getStudentNumber();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.department = student.getDepartment();
        this.yearOfStudy = student.getYearOfStudy();
        this.averageGrade = student.getAverageGrade();
        this.preferredLocation = student.getPreferredLocation();
        this.skills = student.getSkills();
        this.interests = student.getInterests();
    }
}
