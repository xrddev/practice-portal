package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.intership_position.InternshipPositionCreateDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionDto;
import xrddev.practiceportal.model.internship.InternshipPosition;
import java.util.List;

public interface InternshipPositionService {
    void createPosition(InternshipPositionCreateDto dto);
    void deleteByIdAndCompanyEmail(Long id, String companyEmail);
    void updatePosition(Long id, InternshipPositionDto dto, String companyEmail);
    long count();

    List<InternshipPositionDto> getAllByEmailMappedToDto(String companyEmail);
    InternshipPositionDto getByIdAndCompanyEmailMappedToDto(Long id, String email);

    List<InternshipPositionDto> getAllMappedToDto();
    void deleteById(Long id);

}