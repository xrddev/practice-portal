package xrddev.practiceportal.service.internship_assigment;

import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CombinedInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CompanyInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.ProfessorInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface InternshipAssignmentService {
    List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto();

   InternshipAssignmentDashboardDto getByStudentEmailMappedToDashboardDto(String email);

   List<InternshipAssignmentDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String email);

    void deleteById(Long id);

    List<InternshipAssignmentDashboardDto> getAllByProfessorEmailMappedToDashboardDto(String professorEmail);

    void saveStudentEvaluation(String studentEmail, StudentInternshipEvaluationDashboardDto dto);

    void saveCompanyEvaluation(Long assigmentID, Long studentID, String companyEmail, CompanyInternshipEvaluationDashboardDto dto);

    void saveProfessorEvaluation(Long assigmentID, Long studentID, String professorEmail, ProfessorInternshipEvaluationDashboardDto dto);

    CombinedInternshipEvaluationDashboardDto getCombinedEvaluation(Long assigmentID);

    InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto(Long assigmentID, Long studentID, String companyEmail);

    InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto(Long assigmentID, Long studentID, String professorEmail);

}