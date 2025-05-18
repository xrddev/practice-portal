package xrddev.practiceportal.dto.internship_evaluations;

import jakarta.persistence.*;
import lombok.Data;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_evaluations.CompanyInternshipEvaluation;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

@Data
public class InternshipEvaluationCompanyDashboardDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "assignment_id", nullable = false, unique = true)
    private InternshipAssignment assignment;

    @Enumerated(EnumType.STRING)
    private Rating motivation;

    @Enumerated(EnumType.STRING)
    private Rating effectiveness;

    @Enumerated(EnumType.STRING)
    private Rating efficiency;

    @Enumerated(EnumType.STRING)
    private OverallGrade overallGrade;

    private String comments;

    public InternshipEvaluationCompanyDashboardDto(CompanyInternshipEvaluation companyInternshipEvaluation){
        this.id = companyInternshipEvaluation.getId();
        this.motivation = companyInternshipEvaluation.getMotivation();
        this.effectiveness = companyInternshipEvaluation.getEffectiveness();
        this.efficiency = companyInternshipEvaluation.getEfficiency();
        this.overallGrade = companyInternshipEvaluation.getOverallGrade();
        this.comments = companyInternshipEvaluation.getComments();
    }
}
