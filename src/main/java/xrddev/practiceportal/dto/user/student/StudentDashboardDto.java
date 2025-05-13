package xrddev.practiceportal.dto.user.student;

import lombok.Data;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.user.Student;

import java.util.List;

@Data
public class StudentDashboardDto {

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
