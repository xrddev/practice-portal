package xrddev.practiceportal.controller.registration.company;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.service.api.CompanyService;


@Controller
@RequestMapping("/public/register/company")
public class CompanyRegistrationController extends RegistrationSessionHelper {
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
                super.getEmail(session),
                super.getPassword(session),
                companyName,
                address,
                phone,
                website,
                internshipCoordinator,
                internshipCoordinatorEmail);

        super.clearSession(session);
        return "redirect:/public/register/success";
    }
}