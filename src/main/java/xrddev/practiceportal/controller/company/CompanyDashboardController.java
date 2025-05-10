package xrddev.practiceportal.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.intership_position.InternshipPositionResponseDto;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

@Controller
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;

    public CompanyDashboardController(CompanyService companyService, InternshipPositionService internshipPositionService) {
        this.companyService = companyService;
        this.internshipPositionService = internshipPositionService;
    }

    @GetMapping("/company/dashboard")
    public String dashboard(Model model, Principal principal) {
        String companyEmail = principal.getName();

        var company = companyService.findByEmail(companyEmail).orElseThrow(() -> new RuntimeException("Company not found"));
        List<InternshipPositionResponseDto> internshipPositionResponseDto = internshipPositionService
                .getByCompanyEmail(companyEmail).stream().map(InternshipPositionResponseDto::new).toList();

        model.addAttribute("companyDto", new CompanyDashboardDto(company));
        model.addAttribute("internshipPositionResponseDto",internshipPositionResponseDto);
        return "company/dashboard";
    }
}