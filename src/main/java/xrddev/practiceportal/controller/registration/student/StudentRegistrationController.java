package xrddev.practiceportal.controller.registration.student;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.dto.student.StudentRegistrationDto;
import xrddev.practiceportal.service.student.StudentService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/public/register/student")
public class StudentRegistrationController extends RegistrationSessionHelper {

    private final StudentService studentService;

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

