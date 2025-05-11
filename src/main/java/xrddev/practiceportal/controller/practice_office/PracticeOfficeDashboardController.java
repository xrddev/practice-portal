package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.api.CompanyService;
import xrddev.practiceportal.service.api.InternshipPositionService;
import xrddev.practiceportal.service.api.StudentService;
import xrddev.practiceportal.service.api.ApplicationPeriodService;

@Controller
@RequestMapping("/practice-office")
@RequiredArgsConstructor
public class PracticeOfficeDashboardController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;
    private final StudentService studentService;
    private final ApplicationPeriodService applicationPeriodService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        return "practice_office/dashboard";
    }
}