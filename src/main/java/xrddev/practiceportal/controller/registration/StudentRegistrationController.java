package xrddev.practiceportal.controller.registration;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.config.SessionAttribute;
import xrddev.practiceportal.service.StudentService;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/public/register/student")
public class StudentRegistrationController {

    private final StudentService studentService;

    public StudentRegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public String showStudentRegistrationForm(Model model) {

        List<String> departments = Arrays.stream(Department.values()).map(Enum::name).toList();
        model.addAttribute(ModelAttributes.DEPARTMENTS, departments);

        List<String> skills = Arrays.stream(Skills.values()).map(Enum::name).toList();
        model.addAttribute(ModelAttributes.SKILLS, skills);

        List<String> interests = Arrays.stream(Interests.values()).map(Enum::name).toList();
        model.addAttribute(ModelAttributes.INTERESTS, interests);

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
                (String) session.getAttribute(SessionAttribute.EMAIL),
                (String) session.getAttribute(SessionAttribute.PASSWORD),
                studentNumber,
                firstName,
                lastName,
                department,
                yearOfStudy,
                averageGrade,
                skills,
                interests,
                preferredLocation);

        session.removeAttribute(SessionAttribute.EMAIL);
        session.removeAttribute(SessionAttribute.PASSWORD);

        return "redirect:/public/register/success";
    }
}

