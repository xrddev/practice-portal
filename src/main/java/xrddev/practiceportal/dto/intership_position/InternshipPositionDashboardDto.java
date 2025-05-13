package xrddev.practiceportal.dto.intership_position;

import lombok.Data;
import xrddev.practiceportal.dto.user.student.StudentDashboardDto;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipPositionDashboardDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Skills> skills;
    private List<Interests> interests;
    private StudentDashboardDto student;

    public InternshipPositionDashboardDto(InternshipPosition internshipPosition){
        this.id = internshipPosition.getId();
        this.title = internshipPosition.getTitle();
        this.description = internshipPosition.getDescription();
        this.startDate = internshipPosition.getStartDate();
        this.endDate = internshipPosition.getEndDate();
        this.skills = internshipPosition.getSkills();
        this.interests = internshipPosition.getInterests();
        this.student = internshipPosition.getStudent() != null ? new StudentDashboardDto(internshipPosition.getStudent()) : null;
    }
}
