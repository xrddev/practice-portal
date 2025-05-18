package xrddev.practiceportal.dto.student;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.student.Student;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentEditDto {

    @NotBlank(message = "First name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "Year of study is required")
    @Min(value = 1)
    @Max(value = 10)
    private Integer yearOfStudy;

    @NotNull(message = "Average grade is required")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    private Double averageGrade;

    @Size(max = 50)
    private String preferredLocation;

    @NotNull(message = "Skills are required")
    private List<Skills> skills;

    @NotNull(message = "Interests are required")
    private List<Interests> interests;

    public StudentEditDto(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.yearOfStudy = student.getYearOfStudy();
        this.averageGrade = student.getAverageGrade();
        this.preferredLocation = student.getPreferredLocation();
        this.skills = student.getSkills();
        this.interests = student.getInterests();
    }
}
