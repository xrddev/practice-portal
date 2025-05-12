package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.user.student.StudentDto;
import xrddev.practiceportal.dto.user.student.StudentRegistrationDto;
import xrddev.practiceportal.model.user.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void registerStudent(StudentRegistrationDto studentRegistrationDto);

    Optional<Student> findByEmail(String email);
    void updateStudent(StudentDto dto, String email);
    long count();
    List<StudentDto> getAllMappedToDto();
    void deleteById(Long id);

    StudentDto getByEmailMappedToDto(String email);

}