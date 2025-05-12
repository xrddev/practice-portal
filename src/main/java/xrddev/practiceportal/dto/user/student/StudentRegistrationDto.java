package xrddev.practiceportal.dto.user.student;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xrddev.practiceportal.dto.user.AbstractUserRegistrationDto;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.enums.Department;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentRegistrationDto extends AbstractUserRegistrationDto {

    @NotBlank(message = "Student number is required.")
    @Size(min = 5, max = 7, message = "Student number must be 5 to 7 characters.")
    private String studentNumber;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name can be up to 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name can be up to 50 characters.")
    private String lastName;

    @NotNull(message = "Department is required.")
    private Department department;

    @Min(value = 1, message = "Year of study must be at least 1.")
    @Max(value = 10, message = "Year of study cannot exceed 10.")
    private int yearOfStudy;

    @NotNull(message = "Average grade is required.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Grade cannot exceed 10.0.")
    private Double averageGrade;

    @NotEmpty(message = "At least one skill is required.")
    private List<Skills> skills;

    @NotEmpty(message = "At least one interest is required.")
    private List<Interests> interests;

    @Size(max = 50, message = "Preferred location can be up to 50 characters.")
    private String preferredLocation;
}
