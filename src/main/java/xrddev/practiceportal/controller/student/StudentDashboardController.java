package xrddev.practiceportal.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.api.StudentService;

import java.security.Principal;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentDashboardController {

    private final StudentService studentService;

    @GetMapping("/dashboard")
    public String viewDashboard(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("STUDENT_DTO", studentService.getByEmailMappedToDto(email));
        return "student/dashboard";
    }
}