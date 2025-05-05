package xrddev.practiceportal.controller.registration;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xrddev.practiceportal.config.SessionAttribute;
import xrddev.practiceportal.service.CompanyService;

@Controller
@RequestMapping("/public/register/company")
public class CompanyRegistrationController {
    private final CompanyService companyService;

    public CompanyRegistrationController(CompanyService companyService) {
        this.companyService = companyService;
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

        companyService.registerCompany(
                (String) session.getAttribute(SessionAttribute.EMAIL),
                (String) session.getAttribute(SessionAttribute.PASSWORD),
                companyName,
                address,
                phone,
                website,
                internshipCoordinator,
                internshipCoordinatorEmail);

        session.removeAttribute(SessionAttribute.EMAIL);
        session.removeAttribute(SessionAttribute.PASSWORD);

        return "redirect:/public/register/success";
    }
}