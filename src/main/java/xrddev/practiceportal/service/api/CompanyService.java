package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.user.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.user.company.CompanyRegistrationDto;
import xrddev.practiceportal.model.user.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void registerCompany(CompanyRegistrationDto companyRegistrationDto);

    void updateCompany(CompanyDashboardDto dto, String email);
    long count();
    CompanyDashboardDto getByEmailMappedToDto(String email);
    Optional<Company> findByEmail(String email);

    List<CompanyDashboardDto> getAllMappedToDto();
    void deleteByEmail(String email);

}