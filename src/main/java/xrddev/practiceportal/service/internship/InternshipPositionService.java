package xrddev.practiceportal.service.internship;

import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionEditDto;
import xrddev.practiceportal.model.internship.InternshipPosition;

import java.util.List;

public interface InternshipPositionService {

    void createPosition(InternshipPositionCreateDto positionDto, String email);

    void updatePosition(Long id, String companyEmail, InternshipPositionEditDto positionDto);

    void deleteByIdAndCompanyEmail(Long id, String companyEmail);

    long count();

    InternshipPositionEditDto getByIdAndCompanyEmailMappedToEditDto(Long id, String companyEmail);

    InternshipPositionEditDto getByIdMappedToEditDto(Long id);

    List<InternshipPositionDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String companyEmail);

    InternshipPositionDashboardDto getByIdAndCompanyEmailMappedToDashboardDto(Long id, String companyEmail);

    List<InternshipPositionDashboardDto> getAllMappedToDashboardDto();

    List<InternshipPositionDashboardDto> getAllByCompanyIdMappedToDashboardDto(Long companyId);

    void deleteById(Long id);

    List<InternshipPosition> getAll();
}