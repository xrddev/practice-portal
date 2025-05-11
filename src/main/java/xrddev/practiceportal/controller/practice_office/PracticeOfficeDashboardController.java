package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.service.api.ApplicationPeriodService;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;
import xrddev.practiceportal.service.api.ProfessorService;
import xrddev.practiceportal.service.api.PracticeOfficeAdminService;
import xrddev.practiceportal.service.api.StudentService;

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
    private final ApplicationPeriodService applicationPeriodService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        var adminDto = practiceOfficeAdminService
                .findByEmail(principal.getName())
                .map(PracticeOfficeAdminDto::new)
                .orElseThrow(() -> new RuntimeException("Practice Office Admin not found"));

        model.addAttribute("ADMIN_DTO", adminDto);
        model.addAttribute("TOTAL_STUDENTS", studentService.count());
        model.addAttribute("TOTAL_COMPANIES", companyService.count());
        model.addAttribute("TOTAL_PROFESSORS", professorService.count());
        model.addAttribute("TOTAL_POSITIONS", internshipPositionService.count());
        return "practice_office/dashboard";
    }
}