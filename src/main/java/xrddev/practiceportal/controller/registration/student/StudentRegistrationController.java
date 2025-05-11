package xrddev.practiceportal.controller.registration.student;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.service.api.StudentService;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/public/register/student")
public class StudentRegistrationController extends RegistrationSessionHelper {

    private final StudentService studentService;
    private Model model;

    public StudentRegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showStudentRegistrationForm() {
        return "register/student_registration";
    }

    @PostMapping
    public String handleStudentRegistration(
            @RequestParam @NotNull @Size(min = 5, max = 7) String studentNumber,
            @RequestParam @NotNull String firstName,              // νέο
            @RequestParam @NotNull String lastName,               // νέο
            @RequestParam @NotNull String department,
            @RequestParam @NotNull int yearOfStudy,
            @RequestParam @NotNull double averageGrade,
            @RequestParam List<String> skills,
            @RequestParam List<String> interests,
            @RequestParam(required = false) String preferredLocation,
            HttpSession session) {

        studentService.registerStudent(
                super.getEmail(session),
                super.getPassword(session),
                studentNumber,
                firstName,
                lastName,
                department,
                yearOfStudy,
                averageGrade,
                skills,
                interests,
                preferredLocation);

        super.clearSession(session);
        return "redirect:/public/register/success";
    }
}

