package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.model.user.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void registerCompany(String email,
                         String password,
                         String companyName,
                         String address,
                         String phone,
                         String website,
                         String internshipCoordinator,
                         String internshipCoordinatorEmail);

    void updateCompany(CompanyDashboardDto dto, String email);
    long count();
    CompanyDashboardDto getByEmailMappedToDto(String email);
    Optional<Company> findByEmail(String email);

    List<CompanyDashboardDto> getAllMappedToDto();
    void deleteByEmail(String email);

}