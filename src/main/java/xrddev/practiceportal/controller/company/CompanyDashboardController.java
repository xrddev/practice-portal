package xrddev.practiceportal.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("company", companyService.getByEmailMappedToDashboardDto(principal.getName()));
        model.addAttribute("positions", internshipPositionService.getAllByCompanyEmailMappedToDashboardDto(principal.getName()));
        return "company/dashboard";
    }
}