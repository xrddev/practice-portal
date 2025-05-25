package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.dto.internship_evaluations.CombinedInternshipEvaluationDashboardDto;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/practice-office/assigned-internships")
public class PracticeOfficeAssignedInternshipsDashboard {

    private final InternshipAssignmentService internshipAssignmentService;

    @GetMapping("/dashboard")
    public String showMatchedPositionsDashboard(Model model) {
        model.addAttribute("matchedAssignments", internshipAssignmentService.getAllMappedToDashboardDto());
        return "practice_office/assigned_internships/dashboard";
    }

    @GetMapping("/evaluations/{assignmentId}")
    public String viewEvaluationsForAssignment(@PathVariable Long assignmentId, Model model) {
        CombinedInternshipEvaluationDashboardDto evaluationsDto = internshipAssignmentService.getCombinedEvaluation(assignmentId);
        model.addAttribute("evaluations", evaluationsDto);
        return "practice_office/assigned_internships/evaluations";
    }


}
