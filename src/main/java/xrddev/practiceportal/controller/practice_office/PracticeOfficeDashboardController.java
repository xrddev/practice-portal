package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xrddev.practiceportal.model.enums.AssignmentStrategy;
import xrddev.practiceportal.service.api.*;

import java.security.Principal;

@Controller
@RequestMapping("/practice-office")
@RequiredArgsConstructor
public class PracticeOfficeDashboardController {

    private final PracticeOfficeAdminService practiceOfficeAdminService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final ProfessorService professorService;
    private final InternshipPositionService internshipPositionService;
    private final StrategyDispatchService strategyDispatchService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("ADMIN_DTO", practiceOfficeAdminService.getByEmailMappedToDto(email));
        model.addAttribute("TOTAL_STUDENTS", studentService.count());
        model.addAttribute("TOTAL_COMPANIES", companyService.count());
        model.addAttribute("TOTAL_PROFESSORS", professorService.count());
        model.addAttribute("TOTAL_POSITIONS", internshipPositionService.count());
        return "practice_office/dashboard";
    }

    @PostMapping("/dashboard/match")
    public String matchInternships(@RequestParam("strategy") String strategy) {
        System.out.println("Click happened");
        strategyDispatchService.dispatchStrategy(AssignmentStrategy.valueOf(strategy.toUpperCase()));
        return "redirect:/practice-office/dashboard";
    }

}
