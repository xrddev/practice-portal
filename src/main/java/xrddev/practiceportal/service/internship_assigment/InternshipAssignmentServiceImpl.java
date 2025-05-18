package xrddev.practiceportal.service.internship_assigment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.repository.InternshipAssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipAssignmentServiceImpl implements InternshipAssignmentService {

    private final InternshipAssignmentRepository internshipAssignmentRepository;

    @Override
    public List<InternshipAssignmentDashboardDto> getAllMappedToDashboardDto() {
        return internshipAssignmentRepository.findAll().stream().map(InternshipAssignmentDashboardDto::new).toList();
    }

    @Override
    public Optional<InternshipAssignment> getByStudentId(Long studentId) {
        return internshipAssignmentRepository.findByStudentId(studentId);
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
}

