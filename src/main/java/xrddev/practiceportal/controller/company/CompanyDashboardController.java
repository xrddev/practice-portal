package xrddev.practiceportal.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

@Controller
@RequestMapping("/company")
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;

    public CompanyDashboardController(CompanyService companyService, InternshipPositionService internshipPositionService) {
        this.companyService = companyService;
        this.internshipPositionService = internshipPositionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String companyEmail = principal.getName();
        model.addAttribute(ModelAttributeKeys.COMPANY_DASHBOARD_DTO, companyService.getByEmailMappedToDto(companyEmail));
        model.addAttribute(ModelAttributeKeys.INTERNSHIP_POSITION_DASHBOARD_DTO, internshipPositionService.getAllByEmailMappedToDto(companyEmail));
        return "company/dashboard";
    }
}