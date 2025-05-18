package xrddev.practiceportal.service.student;

import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.student.StudentDashboardDto;
import xrddev.practiceportal.dto.student.StudentEditDto;
import xrddev.practiceportal.dto.student.StudentRegistrationDto;
import xrddev.practiceportal.model.student.Student;
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
    List<StudentDashboardDto> getAllMappedToDashboardDto();

    @Transactional(readOnly = true)
    Optional<Student> findById(Long id);

    List<Student> getAll();

}