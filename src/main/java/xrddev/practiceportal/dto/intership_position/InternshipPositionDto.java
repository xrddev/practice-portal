package xrddev.practiceportal.dto.intership_position;

import lombok.Data;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.util.Date;
import java.util.List;

@Data
public class InternshipPositionDto {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Skills> skills;
    private List<Interests> interests;
    private boolean available;
    private Long companyId;
    private Long studentId;
    private Long supervisorId;
    private Long evaluationId;


    public InternshipPositionDto(InternshipPosition pos) {
        this.id = pos.getId();
        this.title = pos.getTitle();
        this.description = pos.getDescription();
        this.startDate = pos.getStartDate();
        this.endDate = pos.getEndDate();
        this.skills = pos.getSkills();
        this.interests = pos.getInterests();
        this.available = pos.isAvailable();
        this.companyId = pos.getCompany().getId();
        this.studentId = pos.getStudent() != null
                ? pos.getStudent().getId() : null;
        this.supervisorId = pos.getSupervisor() != null
                ? pos.getSupervisor().getId() : null;
        this.evaluationId = pos.getEvaluation() != null
                ? pos.getEvaluation().getId() : null;
    }
}