package xrddev.practiceportal.controller.registration.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "/register/logout_success";
    }
}