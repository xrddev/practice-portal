package xrddev.practiceportal.controller.registration;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xrddev.practiceportal.config.SessionAttribute;
import xrddev.practiceportal.model.Company;
import xrddev.practiceportal.repository.CompanyRepository;
import xrddev.practiceportal.service.CompanyService;

@Controller
@RequestMapping("/public/register/company")
public class CompanyRegistrationController {

    private final CompanyRepository companyRepository;

    public CompanyRegistrationController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public String showCompanyRegistrationForm() {
        return "register/company_registration";
    }

    @PostMapping
    public String handleCompanyRegistration(
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam(required = false) String website,
            @RequestParam String internshipCoordinator,
            @RequestParam String internshipCoordinatorEmail,
            HttpSession session) {

        Company company = new Company();
        company.setPassword((String) session.getAttribute(SessionAttribute.PASSWORD));
        company.setEmail((String) session.getAttribute(SessionAttribute.EMAIL));
        company.setCompanyName(companyName);
        company.setAddress(address);
        company.setPhone(phone);
        company.setWebsite(website);
        company.setInternshipCoordinator(internshipCoordinator);
        company.setInternshipCoordinatorEmail(internshipCoordinatorEmail);
        companyRepository.save(company);

        session.removeAttribute(SessionAttribute.EMAIL);
        session.removeAttribute(SessionAttribute.PASSWORD);
        return "redirect:/public/register/success";
    }
}