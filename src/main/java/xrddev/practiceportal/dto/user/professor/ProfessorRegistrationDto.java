package xrddev.practiceportal.dto.user.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xrddev.practiceportal.dto.user.AbstractUserRegistrationDto;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessorRegistrationDto extends AbstractUserRegistrationDto {

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name can be up to 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name can be up to 50 characters.")
    private String lastName;

    @NotNull(message = "Department is required.")
    private Department department;

    @NotNull(message = "At least one interest is required.")
    private List<Interests> interests;
}
