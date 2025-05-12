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
import xrddev.practiceportal.dto.user.student.StudentDto;
import xrddev.practiceportal.service.api.StudentService;

import java.security.Principal;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentService studentService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("STUDENT_DTO", studentService.getByEmailMappedToDto(email));
        return "student/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("STUDENT_DTO") StudentDto dto,
            BindingResult bindingResult,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("STUDENT_DTO", dto);
            return "student/edit_profile";
        }

        // Ensure the email is taken from the authenticated user and not from the submitted form for security reasons
        dto.setEmail(principal.getName());

        studentService.updateStudent(dto, principal.getName());
        redirectAttributes.addFlashAttribute("profileUpdated", true);
        return "redirect:/student/dashboard";
    }
}