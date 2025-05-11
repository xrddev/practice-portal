package xrddev.practiceportal.controller.registration.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/register/success")
public class RegistrationSuccessController {

    @GetMapping
    public String showSuccessPage() {
        return "register/register_success";
    }

    @PostMapping("/redirectToIndex")
    public String redirectToIndex() {
        return "redirect:/";
    }
}