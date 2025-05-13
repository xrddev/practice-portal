package xrddev.practiceportal.service.api;

import jakarta.validation.Valid;
import xrddev.practiceportal.dto.user.student.StudentDashboardDto;
import xrddev.practiceportal.dto.user.student.StudentEditDto;
import xrddev.practiceportal.dto.user.student.StudentRegistrationDto;
import xrddev.practiceportal.model.user.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void registerStudent(StudentRegistrationDto studentRegistrationDto);

    Optional<Student> findByEmail(String email);
    long count();
    void deleteById(Long id);

    StudentDashboardDto getByEmailMappedToDashboardDto(String email);
    StudentEditDto getByEmailMappedToEditDto(String email);
    void updateStudent(StudentEditDto studentEditDto, String email);
}