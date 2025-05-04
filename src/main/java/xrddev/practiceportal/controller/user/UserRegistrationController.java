package xrddev.practiceportal.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public/register")
public class UserRegistrationController {

    @GetMapping()
    public String showRegistrationForm() {
        return "register/register";
    }

}