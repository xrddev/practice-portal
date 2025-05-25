package xrddev.practiceportal.dto.professor;

import lombok.Data;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.professor.Professor;

import java.util.List;

@Data
public class ProfessorDashboardDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Department department;
    private List<Interests> interests;

    public ProfessorDashboardDto(Professor professor) {
        if (professor == null) {
            this.id = 0L;
            this.firstName = null;
            this.lastName = null;
            this.email = null;
            this.department = null;
            this.interests = null;
            return;
        }//for testing

        this.id = professor.getId();
        this.firstName = professor.getFirstName();
        this.lastName = professor.getLastName();
        this.email = professor.getEmail();
        this.department = professor.getDepartment();
        this.interests = professor.getInterests();
    }
}
