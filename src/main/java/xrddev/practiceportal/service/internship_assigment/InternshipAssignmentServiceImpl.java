package xrddev.practiceportal.service.internship_assigment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CompanyInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_evaluations.CompanyInternshipEvaluation;
import xrddev.practiceportal.model.internship_evaluations.StudentInternshipEvaluation;
import xrddev.practiceportal.repository.InternshipAssignmentRepository;
import xrddev.practiceportal.repository.StudentInternshipEvaluationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipAssignmentServiceImpl implements InternshipAssignmentService {

    private final InternshipAssignmentRepository internshipAssignmentRepository;
    private final StudentInternshipEvaluationRepository studentEvaluationRepository;


    @Override
    public List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto() {
        return internshipAssignmentRepository.findAll().stream().map(InternshipAssignmentDashboardDto::new).toList();
    }

    @Override
    public InternshipAssignmentDashboardDto getByStudentEmailMappedToDashboardDto(String email) {
        return internshipAssignmentRepository
                .findByStudentEmail(email)
                .map(InternshipAssignmentDashboardDto::new)
                .orElse(null); // ➤ Διορθωμένο
    }


    @Override
    public void deleteById(Long id) {
        internshipAssignmentRepository.deleteById(id);
    }

    @Override
    public List<InternshipAssignmentDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String companyEmail){
        return internshipAssignmentRepository.findAllByPositionCompanyEmail(companyEmail).stream().map(InternshipAssignmentDashboardDto::new).toList();
    }


    @Override
    public void saveStudentEvaluation(String studentEmail, StudentInternshipEvaluationDashboardDto dto) {
        InternshipAssignment assignment = internshipAssignmentRepository
                .findByStudentEmail(studentEmail)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found for student: " + studentEmail));

        StudentInternshipEvaluation evaluation = assignment.getStudentEvaluation();

        if (evaluation == null) {
            evaluation = new StudentInternshipEvaluation();
        }

        evaluation.setRating(dto.getRating());
        evaluation.setComment(dto.getComment());

        assignment.setStudentEvaluation(evaluation);
        internshipAssignmentRepository.save(assignment);
    }

    @Override
    public void saveCompanyEvaluation(Long assigmentID, Long studentID , String companyEmail, CompanyInternshipEvaluationDashboardDto dto) {
        InternshipAssignment assignment = internshipAssignmentRepository
                .findByIdAndStudentIdAndPositionCompanyEmail(assigmentID, studentID, companyEmail)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        CompanyInternshipEvaluation evaluation = assignment.getCompanyEvaluation();

        if (evaluation == null) {
            evaluation = new CompanyInternshipEvaluation();
        }

        evaluation.setMotivation(dto.getMotivation());
        evaluation.setEffectiveness(dto.getEffectiveness());
        evaluation.setEfficiency(dto.getEfficiency());
        evaluation.setOverallGrade(dto.getOverallGrade());
        evaluation.setComments(dto.getComments());

        assignment.setCompanyEvaluation(evaluation);
        internshipAssignmentRepository.save(assignment);
    }

    @Override
    public InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDMappedToDashboardDto(Long assignmentId, Long studentId, String companyEmail) {
        return internshipAssignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(assignmentId, studentId, companyEmail)
                .map(InternshipAssignmentDashboardDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));
    }
}

