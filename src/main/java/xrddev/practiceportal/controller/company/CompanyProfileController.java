package xrddev.practiceportal.controller.company;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.dto.user.company.CompanyEditDto;
import xrddev.practiceportal.service.company.CompanyService;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyProfileController {

    private final CompanyService companyService;


    @GetMapping("/edit-profile")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("company", companyService.getByEmailMappedToEditDto(principal.getName()));
        return "company/edit_profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @Valid @ModelAttribute("company")CompanyEditDto companyEditDto,
            BindingResult bindingResult,
            Principal principal) {

        if (bindingResult.hasErrors())
            return "company/edit_profile";

        companyService.updateCompany(companyEditDto, principal.getName());
        return "redirect:/company/dashboard";
    }
}