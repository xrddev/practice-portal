package xrddev.practiceportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.OverallGrade;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "internship_evaluation")
public class InternshipEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "overall_grade", nullable = false)
    @NotNull(message = "Overall grade cannot be null!")
    private OverallGrade overallGrade; // Συνολικός βαθμός αξιολόγησης

    @Column(name = "motivation_grade", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Motivation grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Motivation grade must not exceed 10.0.")
    private Double motivationGrade; // Βαθμός για Motivation

    @Column(name = "efficiency_grade", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Efficiency grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Efficiency grade must not exceed 10.0.")
    private Double efficiencyGrade; // Βαθμός για Efficiency

    @Column(name = "effectiveness_grade", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Effectiveness grade must be at least 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "Effectiveness grade must not exceed 10.0.")
    private Double effectivenessGrade; // Βαθμός για Effectiveness

    @OneToOne(mappedBy = "evaluation")
    private InternshipPosition internshipPosition; // Η θέση πρακτικής που αφορά η αξιολόγηση
}