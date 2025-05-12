package xrddev.practiceportal.dto.user.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.user.Professor;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ProfessorDto {
    private Long id;

    @NotNull(message = "First name is required.")
    @Size(max = 100, message = "First name can be up to 100 characters.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Size(max = 100, message = "Last name can be up to 100 characters.")
    private String lastName;

    @Email(message = "Email must be valid.")
    private String email;

    @NotNull(message = "Department is required.")
    private Department department;

    @NotNull(message = "At least one interest is required.")
    private List<Interests> interests;

    private List<InternshipPositionDto> supervisedPositions;

    public ProfessorDto(Professor professor) {
        this.firstName = professor.getFirstName();
        this.lastName = professor.getLastName();
        this.email = professor.getEmail();
        this.department = professor.getDepartment();
        this.interests = professor.getInterests();
        this.id = professor.getId();
        this.supervisedPositions = professor.getSupervisedPositions()
                .stream()
                .map(InternshipPositionDto::new)
                .collect(Collectors.toList());
    }
}
