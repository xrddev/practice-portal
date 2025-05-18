package xrddev.practiceportal.controller.api_manual_post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.service.professor.ProfessorService;

import java.util.List;

@Validated
@RestController
@RequestMapping(
        path     = "/public/api/professors",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ProfessorApiController {

    private final ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessor(@RequestBody @Valid ProfessorRegistrationDto dto) {
        professorService.registerProfessor(dto);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessorsBatch(
            @RequestBody @Valid List<ProfessorRegistrationDto> dtoList) {
        dtoList.forEach(professorService::registerProfessor);
    }
}