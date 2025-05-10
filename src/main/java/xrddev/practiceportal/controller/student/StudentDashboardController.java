package xrddev.practiceportal.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xrddev.practiceportal.dto.student.StudentDto;
import xrddev.practiceportal.model.user.Student;
import xrddev.practiceportal.service.api.StudentService;

@Controller
@RequiredArgsConstructor
public class StudentDashboardController {

    private final StudentService studentService;

    @GetMapping("/student/dashboard")
    public String showDashboard(@AuthenticationPrincipal User principal, Model model) {
        Student student = studentService
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentDto dto = new StudentDto(student);
        model.addAttribute("student", dto);

        return "student/dashboard";
    }
}