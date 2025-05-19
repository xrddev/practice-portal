package xrddev.practiceportal.service.internship_assigment;

import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.CompanyInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

import java.util.List;
import java.util.Optional;

public interface InternshipAssignmentService {
    List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto();

   InternshipAssignmentDashboardDto getByStudentEmailMappedToDashboardDto(String email);

   List<InternshipAssignmentDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String email);

    void deleteById(Long id);

    void saveStudentEvaluation(String studentEmail, StudentInternshipEvaluationDashboardDto dto);

    void saveCompanyEvaluation(Long assigmentID, Long studentID, String companyEmail, CompanyInternshipEvaluationDashboardDto dto);

    InternshipAssignmentDashboardDto getByAssigmentIDAndStudentIDMappedToDashboardDto(Long assigmentID, Long studentID, String companyEmail);


}