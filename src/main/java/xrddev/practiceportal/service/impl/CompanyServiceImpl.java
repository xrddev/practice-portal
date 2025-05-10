package xrddev.practiceportal.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.user.Company;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.api.CompanyRepository;
import xrddev.practiceportal.service.api.CompanyService;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public void registerCompany(String email,
                                String password,
                                String companyName,
                                String address,
                                String phone,
                                String website,
                                String internshipCoordinator,
                                String internshipCoordinatorEmail) {
        Company company = new Company();
        company.setRole(UserRole.COMPANY);
        company.setEmail(email);
        company.setPassword(password);
        company.setCompanyName(companyName);
        company.setAddress(address);
        company.setPhone(phone);
        company.setWebsite(website);
        company.setInternshipCoordinator(internshipCoordinator);
        company.setInternshipCoordinatorEmail(internshipCoordinatorEmail);
        companyRepository.save(company);
    }

    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
}