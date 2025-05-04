package xrddev.practiceportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.Company;
import xrddev.practiceportal.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Μέθοδος που αποθηκεύει δεδομένα
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}