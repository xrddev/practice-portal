package xrddev.practiceportal.dto.student;

import jakarta.validation.constraints.*;
import lombok.*;
import xrddev.practiceportal.model.user.Student;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;

    @NotNull(message = "Student number cannot be null!")
    @Size(min = 5, max = 7, message = "Student number must be between 5 and 7 characters.")
    private String studentNumber;

    @NotNull(message = "First name cannot be null!")
    @Size(max = 50, message = "First name can have up to 50 characters.")
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    @Size(max = 50, message = "Last name can have up to 50 characters.")
    private String lastName;

    @NotNull(message = "Department cannot be null!")
    private Department department;

    @Min(value = 1, message = "Year of study must be at least 1.")
    @Max(value = 10, message = "Year of study cannot exceed 10.")
    private Integer yearOfStudy;

    private List<Skills> skills;

    private List<Interests> interests;

    @NotNull(message = "Average grade cannot be null!")
    @DecimalMin(value = "0.0", inclusive = true, message = "Average grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Average grade must not exceed 10.0.")
    private Double averageGrade;

    @Size(max = 50, message = "Preferred location can have up to 50 characters.")
    private String preferredLocation;


    public StudentDto(Student student) {
        this.id = student.getId();
        this.studentNumber = student.getStudentNumber();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.department = student.getDepartment();
        this.yearOfStudy = student.getYearOfStudy();
        this.skills = student.getSkills();
        this.interests = student.getInterests();
        this.averageGrade = student.getAverageGrade();
        this.preferredLocation = student.getPreferredLocation();
    }
}