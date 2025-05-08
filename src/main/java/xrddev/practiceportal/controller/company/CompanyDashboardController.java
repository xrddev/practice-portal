package xrddev.practiceportal.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.model.Company;
import xrddev.practiceportal.service.CompanyService;

@Controller
@RequiredArgsConstructor
public class CompanyDashboardController {

    private final CompanyService companyService;

    @GetMapping("/company/dashboard")
    public String showDashboard(
            @AuthenticationPrincipal User principal,
            Model model
    ) {
        Company company = companyService
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        model.addAttribute("company", new CompanyDashboardDto(company));
        return "company/dashboard";
    }
}