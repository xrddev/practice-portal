package xrddev.practiceportal.controller.registration.student;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.dto.user.student.StudentRegistrationDto;
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

    public StudentRegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showStudentRegistrationForm(Model model) {
        model.addAttribute("student", new StudentRegistrationDto());
        return "register/student_registration";
    }

    @PostMapping
    public String handleStudentRegistration(
            @ModelAttribute("student") @Valid StudentRegistrationDto studentRegistrationDto,
            BindingResult bindingResult,
            HttpSession session) {

        if (bindingResult.hasErrors())
            return "register/student_registration";


        studentRegistrationDto.setEmail(super.getEmail(session));
        studentRegistrationDto.setPassword(super.getPassword(session));
        studentService.registerStudent(studentRegistrationDto);
        super.clearSession(session);
        return "redirect:/public/register/success";
    }
}

