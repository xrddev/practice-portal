package xrddev.practiceportal.model.internship_assigment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.enums.ProfessorMatchingOptions;
import xrddev.practiceportal.model.internship_evaluations.CompanyInternshipEvaluation;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.model.internship_evaluations.ProfessorInternshipEvaluation;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.model.student.Student;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "internship_assignment")
public class InternshipAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "position_id", nullable = false)
    private InternshipPosition position;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private LocalDate assignedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_match_strategy", nullable = false, length = 20)
    private InternshipMatchingOptions StudentMatchStrategy;

    @Enumerated(EnumType.STRING)
    @Column(name = "professor_match_strategy", nullable = false, length = 20)
    private ProfessorMatchingOptions ProfessorMatchStrategy;

    @OneToOne(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private CompanyInternshipEvaluation companyEvaluation;

    @OneToOne(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfessorInternshipEvaluation professorEvaluation;
}