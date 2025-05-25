package xrddev.practiceportal.dto.internship_evaluations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CombinedInternshipEvaluationDashboardDto {
    private StudentInternshipEvaluationDashboardDto studentEvaluation;
    private CompanyInternshipEvaluationDashboardDto companyEvaluation;
    private ProfessorInternshipEvaluationDashboardDto professorEvaluation;


    public CombinedInternshipEvaluationDashboardDto(
            StudentInternshipEvaluationDashboardDto studentEvaluation,
            CompanyInternshipEvaluationDashboardDto companyEvaluation,
            ProfessorInternshipEvaluationDashboardDto professorEvaluation) {
        this.studentEvaluation = studentEvaluation;
        this.companyEvaluation = companyEvaluation;
        this.professorEvaluation = professorEvaluation;

    }
}
