package xrddev.practiceportal.controller.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentService;
import xrddev.practiceportal.service.student.StudentService;


import java.security.Principal;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentDashboardController {

    private final StudentService studentService;
    private final InternshipAssignmentService internshipAssignmentService;

    @GetMapping("/dashboard")
    public String viewDashboard(Model model, Principal principal) {
        model.addAttribute("internship_assigment",internshipAssignmentService.getByStudentEmailMappedToDashboardDto(principal.getName()));
        model.addAttribute("student", studentService.getByEmailMappedToDashboardDto(principal.getName()));
        return "student/dashboard";
    }

    @GetMapping("/evaluation")
    public String showEvaluationForm(Model model, Principal principal) {
        model.addAttribute("evaluation", new StudentInternshipEvaluationDashboardDto());
        return "student/evaluation";
    }

    @PostMapping("/evaluation")
    public String submitEvaluation(@ModelAttribute("evaluation") @Valid StudentInternshipEvaluationDashboardDto evaluationDto,
                                   BindingResult bindingResult,
                                   Principal principal) {
        if (bindingResult.hasErrors()) {
            return "student/evaluation";
        }

        internshipAssignmentService.saveStudentEvaluation(principal.getName(), evaluationDto);
        return "redirect:/student/dashboard";
    }
}