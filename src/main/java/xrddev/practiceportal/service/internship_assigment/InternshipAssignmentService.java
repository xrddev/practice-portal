package xrddev.practiceportal.service.internship_assigment;

import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;

import java.util.List;
import java.util.Optional;

public interface InternshipAssignmentService {
    List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto();

    Optional<InternshipAssignment> getByStudentId(Long studentId);

   InternshipAssignmentDashboardDto getByStudentEmailMappedToDashboardDto(String email);

   List<InternshipAssignmentDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String email);

    void deleteById(Long id);

}