package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDashboardDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionEditDto;

import java.util.List;

public interface InternshipPositionService {
    void createPosition(InternshipPositionCreateDto dto);
    void updatePosition(InternshipPositionEditDto dto, Long id);


    InternshipPositionEditDto getByIdAndCompanyEmailMappedToEditDto(Long id, String email);
    void deleteByIdAndCompanyEmail(Long id, String companyEmail);

    void deleteById(Long id);
    long count();


    InternshipPositionEditDto getByIdMappedToEditDto(Long id);

    List<InternshipPositionDashboardDto> getAllByCompanyEmailMappedToDashboardDto(String email);

}