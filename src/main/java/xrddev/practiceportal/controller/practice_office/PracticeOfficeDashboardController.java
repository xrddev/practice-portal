package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xrddev.practiceportal.model.enums.InternshipMatchingOptions;
import xrddev.practiceportal.model.internship.InternshipAssignment;
import xrddev.practiceportal.repository.InternshipAssignmentRepository;
import xrddev.practiceportal.service.company.CompanyService;
import xrddev.practiceportal.service.internship.InternshipPositionService;
import xrddev.practiceportal.service.practice_office.PracticeOfficeAdminService;
import xrddev.practiceportal.service.professor.ProfessorService;
import xrddev.practiceportal.service.strategies.InternshipAssigmentStrategyDispatcher;
import xrddev.practiceportal.service.student.StudentService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/practice-office")
@RequiredArgsConstructor
public class PracticeOfficeDashboardController {

    private final PracticeOfficeAdminService practiceOfficeAdminService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final ProfessorService professorService;
    private final InternshipPositionService internshipPositionService;
    private final InternshipAssigmentStrategyDispatcher internshipAssigmentStrategyDispatcher;
    private final InternshipAssignmentRepository internshipAssignmentRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("ADMIN_DTO", practiceOfficeAdminService.getByEmailMappedToDto(email));
        model.addAttribute("TOTAL_STUDENTS", studentService.count());
        model.addAttribute("TOTAL_COMPANIES", companyService.count());
        model.addAttribute("TOTAL_PROFESSORS", professorService.count());
        model.addAttribute("TOTAL_POSITIONS", internshipPositionService.count());
        model.addAttribute("TOTAL_MATCHED_POSITIONS", internshipAssignmentRepository.count());
        return "practice_office/dashboard";
    }

    @Transactional
    @PostMapping("/dashboard/match")
    public String matchInternships(@RequestParam("strategy") String strategy) {
        List<InternshipAssignment> matchedInternships =
                internshipAssigmentStrategyDispatcher.dispatch(InternshipMatchingOptions.valueOf(strategy.toUpperCase()));

        matchedInternships.forEach(assignment -> assignment.getPosition().setAssigned(true));
        internshipAssignmentRepository.saveAll(matchedInternships);
        return "redirect:/practice-office/dashboard";
    }

}
