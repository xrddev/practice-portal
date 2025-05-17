package xrddev.practiceportal.controller.api_manual_post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.user.company.CompanyRegistrationDto;
import xrddev.practiceportal.service.company.CompanyService;

import java.util.List;

@Validated
@RestController
@RequestMapping(
    path     = "/public/api/companies",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CompanyApiController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(
        @RequestBody @Valid CompanyRegistrationDto dto
    ) {
        companyService.registerCompany(dto);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompaniesBatch(
        @RequestBody @Valid List<CompanyRegistrationDto> dtos
    ) {
        dtos.forEach(companyService::registerCompany);
    }
}