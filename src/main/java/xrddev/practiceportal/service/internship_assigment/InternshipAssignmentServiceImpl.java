package xrddev.practiceportal.service.internship_assigment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CombinedInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CompanyInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.ProfessorInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_evaluations.CompanyInternshipEvaluation;
import xrddev.practiceportal.model.internship_evaluations.ProfessorInternshipEvaluation;
import xrddev.practiceportal.model.internship_evaluations.StudentInternshipEvaluation;
import xrddev.practiceportal.repository.InternshipAssignmentRepository;
import xrddev.practiceportal.repository.StudentInternshipEvaluationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipAssignmentServiceImpl implements InternshipAssignmentService {

    private final InternshipAssignmentRepository internshipAssignmentRepository;


    @Override
    public List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto() {
        return internshipAssignmentRepository.findAll().stream().map(InternshipAssignmentDashboardDto::new).toList();
    }

    @Override
    public InternshipAssignmentDashboardDto getByStudentEmailMappedToDashboardDto(String email) {
        return internshipAssignmentRepository
                .findByStudentEmail(email)
                .map(InternshipAssignmentDashboardDto::new)
                .orElse(null);
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
    public List<InternshipAssignmentDashboardDto> getAllByProfessorEmailMappedToDashboardDto(String professorEmail){
        return internshipAssignmentRepository.findAllByProfessorEmail(professorEmail).stream().map(InternshipAssignmentDashboardDto::new).toList();
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
    public void saveProfessorEvaluation(Long assigmentID, Long studentID, String professorEmail, ProfessorInternshipEvaluationDashboardDto dto) {
        InternshipAssignment assignment = internshipAssignmentRepository.
                findByIdAndStudentIdAndProfessorEmail(assigmentID, studentID, professorEmail)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        ProfessorInternshipEvaluation evaluation = assignment.getProfessorEvaluation();

        if (evaluation == null) {
            evaluation = new ProfessorInternshipEvaluation();
        }

        evaluation.setMotivation(dto.getMotivation());
        evaluation.setEffectiveness(dto.getEffectiveness());
        evaluation.setEfficiency(dto.getEfficiency());
        evaluation.setOverallGrade(dto.getOverallGrade());
        evaluation.setComments(dto.getComments());

        assignment.setProfessorEvaluation(evaluation);
        internshipAssignmentRepository.save(assignment);
    }


    @Override
    public CombinedInternshipEvaluationDashboardDto getCombinedEvaluation(Long assigmentID) {
        InternshipAssignment assignment = internshipAssignmentRepository.findById(assigmentID).orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        CombinedInternshipEvaluationDashboardDto dto = new CombinedInternshipEvaluationDashboardDto();
        dto.setStudentEvaluation(new StudentInternshipEvaluationDashboardDto(assignment.getStudentEvaluation()));
        dto.setCompanyEvaluation(new CompanyInternshipEvaluationDashboardDto(assignment.getCompanyEvaluation()));
        dto.setProfessorEvaluation(new ProfessorInternshipEvaluationDashboardDto(assignment.getProfessorEvaluation()));
        return  dto;
    }


    @Override
    public InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto(Long assignmentId, Long studentId, String companyEmail) {
        return internshipAssignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(assignmentId, studentId, companyEmail)
                .map(InternshipAssignmentDashboardDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));
    }

    @Override
    public InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto(Long assigmentID, Long studentID, String professorEmail){
        return internshipAssignmentRepository.findByIdAndStudentIdAndProfessorEmail(assigmentID, studentID, professorEmail)
                .map(InternshipAssignmentDashboardDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));
    }


}

