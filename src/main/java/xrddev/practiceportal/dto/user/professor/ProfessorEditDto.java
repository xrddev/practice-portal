package xrddev.practiceportal.dto.user.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.user.Professor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProfessorEditDto {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotEmpty(message = "At least one interest is required.")
    private List<Interests> interests;

    public ProfessorEditDto(Professor professor) {
        this.firstName = professor.getFirstName();
        this.lastName = professor.getLastName();
        this.interests = professor.getInterests();
    }
}
