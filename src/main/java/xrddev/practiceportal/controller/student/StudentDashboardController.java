package xrddev.practiceportal.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}