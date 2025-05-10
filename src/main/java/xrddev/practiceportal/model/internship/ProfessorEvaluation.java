package xrddev.practiceportal.model.internship;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.OverallGrade;

@Entity
@Table(name = "professor_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @OneToOne(mappedBy = "professorEvaluation")
    private InternshipPosition internshipPosition;
}