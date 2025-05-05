package xrddev.practiceportal.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.Company;
import xrddev.practiceportal.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void registerCompany(String email, String password, String companyName, String address, String phone,
                                String website, String internshipCoordinator, String internshipCoordinatorEmail) {

        Company company = new Company();
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

}
