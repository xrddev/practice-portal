package xrddev.practiceportal.dto.user.professor;

import lombok.Data;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.user.Professor;

import java.util.List;

@Data
public class ProfessorDashboardDto {
    private String firstName;
    private String lastName;
    private String email;
    private Department department;
    private List<Interests> interests;

    public ProfessorDashboardDto(Professor professor) {
        this.firstName = professor.getFirstName();
        this.lastName = professor.getLastName();
        this.email = professor.getEmail();
        this.department = professor.getDepartment();
        this.interests = professor.getInterests();
    }
}
