package xrddev.practiceportal.model.internship;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xrddev.practiceportal.model.enums.OverallGrade;

@Entity
@Table(name = "company_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @OneToOne(mappedBy = "companyEvaluation")
    private InternshipPosition internshipPosition;
}