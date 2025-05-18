package xrddev.practiceportal.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.company.CompanyService;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentService;
import xrddev.practiceportal.service.periods.EvaluationPeriodService;
import xrddev.practiceportal.service.internship.InternshipPositionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;
    private final EvaluationPeriodService evaluationPeriodService;
    private final InternshipAssignmentService internshipAssignmentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("company", companyService.getByEmailMappedToDashboardDto(email));
        model.addAttribute("positions", internshipPositionService.getAllByCompanyEmailMappedToDashboardDto(email));
        model.addAttribute("matchedAssignments", internshipAssignmentService.getAllByCompanyEmailMappedToDashboardDto(email));
        return "company/dashboard";
    }

}