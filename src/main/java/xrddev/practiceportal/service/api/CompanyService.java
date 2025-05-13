package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.user.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.user.company.CompanyEditDto;
import xrddev.practiceportal.dto.user.company.CompanyRegistrationDto;
import xrddev.practiceportal.model.user.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void registerCompany(CompanyRegistrationDto companyRegistrationDto);

    void updateCompany(CompanyEditDto dto, String email);
    long count();
    CompanyDashboardDto getByEmailMappedToDashboardDto(String email);
    CompanyEditDto getByEmailMappedToEditDto(String email);
    Optional<Company> findByEmail(String email);

    List<CompanyDashboardDto> getAllMappedToDto();
    void deleteByEmail(String email);

}