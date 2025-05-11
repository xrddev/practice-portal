package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.model.user.Company;
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
    Optional<Company> findByEmail(String email);

    void updateCompany(CompanyDashboardDto dto, String email);
    long count();
}