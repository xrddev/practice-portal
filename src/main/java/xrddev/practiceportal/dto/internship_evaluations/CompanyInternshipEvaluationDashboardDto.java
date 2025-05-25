package xrddev.practiceportal.dto.internship_evaluations;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_evaluations.CompanyInternshipEvaluation;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

@NoArgsConstructor
@Data
public class CompanyInternshipEvaluationDashboardDto {

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

    @Pattern(regexp = "^(10(\\.0+)?|[0-9](\\.\\d+)?)$", message = "Grade must be between 0 and 10")
    private String overallGrade;

    private String comments;

    public CompanyInternshipEvaluationDashboardDto(CompanyInternshipEvaluation evaluation) {
        if(evaluation == null) return;
        this.motivation = evaluation.getMotivation();
        this.effectiveness = evaluation.getEffectiveness();
        this.efficiency = evaluation.getEfficiency();
        this.overallGrade = evaluation.getOverallGrade();
        this.comments = evaluation.getComments();
    }
}
