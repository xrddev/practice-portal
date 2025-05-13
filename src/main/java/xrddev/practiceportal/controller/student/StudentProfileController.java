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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.dto.user.student.StudentEditDto;
import xrddev.practiceportal.service.api.StudentService;

import java.security.Principal;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentService studentService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("student", studentService.getByEmailMappedToEditDto(principal.getName()));
        return "student/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("STUDENT_DTO")StudentEditDto studentEditDto,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            return "student/edit_profile";
        }

        studentService.updateStudent(studentEditDto, principal.getName());
        return "redirect:/student/dashboard";
    }
}
