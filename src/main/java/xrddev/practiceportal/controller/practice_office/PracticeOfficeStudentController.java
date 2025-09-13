package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.service.student.StudentService;

import java.security.Principal;

@Controller
@RequestMapping("/practice-office/students")
@RequiredArgsConstructor
public class PracticeOfficeStudentController {

    private final StudentService studentService;

    @GetMapping("/dashboard")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllMappedToDashboardDto());
        return "practice_office/students/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/practice-office/students/dashboard";
    }
}