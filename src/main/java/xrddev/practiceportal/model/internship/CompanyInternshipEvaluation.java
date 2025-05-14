package xrddev.practiceportal.model.internship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import xrddev.practiceportal.model.enums.OverallGrade;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship.InternshipAssignment;

@Entity
@Getter
@Setter
@Table(name = "company_internship_evaluation")
public class CompanyInternshipEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "assignment_id", nullable = false, unique = true)
    private InternshipAssignment assignment;

    @NotNull(message = "Motivation rating is required.")
    @Enumerated(EnumType.STRING)
    private Rating motivation;

    @NotNull(message = "Effectiveness rating is required.")
    @Enumerated(EnumType.STRING)
    private Rating effectiveness;

    @NotNull(message = "Efficiency rating is required.")
    @Enumerated(EnumType.STRING)
    private Rating efficiency;

    @NotNull(message = "Overall grade is required.")
    @Enumerated(EnumType.STRING)
    private OverallGrade overallGrade;

    @Size(max = 500, message = "Comments can be up to 500 characters.")
    private String comments;
}
