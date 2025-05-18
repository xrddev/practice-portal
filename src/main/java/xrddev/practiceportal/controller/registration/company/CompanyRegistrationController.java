package xrddev.practiceportal.controller.registration.company;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import xrddev.practiceportal.controller.registration.common.RegistrationSessionHelper;
import xrddev.practiceportal.dto.company.CompanyRegistrationDto;
import xrddev.practiceportal.service.company.CompanyService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/public/register/company")
public class CompanyRegistrationController extends RegistrationSessionHelper {

    private final CompanyService companyService;

    @GetMapping
    public String showCompanyRegistrationForm(Model model) {
        model.addAttribute("company", new CompanyRegistrationDto());
        return "register/company_registration";
    }

    @PostMapping
    public String handleCompanyRegistration(
            @ModelAttribute("company") @Valid CompanyRegistrationDto companyRegistrationDto,
            BindingResult bindingResult,
            HttpSession session) {


        if (bindingResult.hasErrors())
            return "register/company_registration";

        companyRegistrationDto.setEmail(super.getEmail(session));
        companyRegistrationDto.setPassword(super.getPassword(session));
        companyService.registerCompany(companyRegistrationDto);
        super.clearSession(session);
        return "redirect:/public/register/success";
    }
}