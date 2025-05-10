package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.model.internship.InternshipPosition;
import java.util.List;

public interface InternshipPositionService {
    void createPosition(InternshipPositionCreateDto dto);
    List<InternshipPosition> getByCompanyEmail(String email);
    void deleteByIdAndCompanyEmail(Long id, String companyEmail);
    InternshipPosition getByIdAndCompanyEmail(Long id, String companyEmail);
    void updatePosition(Long id, InternshipPositionDashboardDto dto, String companyEmail);

}