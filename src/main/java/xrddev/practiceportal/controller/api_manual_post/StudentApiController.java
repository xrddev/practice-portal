package xrddev.practiceportal.controller.api_manual_post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.dto.user.student.StudentRegistrationDto;
import xrddev.practiceportal.service.api.StudentService;

import java.util.List;

@Validated
@RestController
@RequestMapping(
    path     = "/public/api/students",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class StudentApiController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(
        @RequestBody @Valid StudentRegistrationDto dto
    ) {
        studentService.registerStudent(dto);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudentsBatch(
        @RequestBody @Valid List<StudentRegistrationDto> dtos
    ) {
        dtos.forEach(studentService::registerStudent);
    }
}