package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.student.StudentDto;
import xrddev.practiceportal.model.user.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void registerStudent(String email,
                         String password,
                         String studentNumber,
                         String firstName,
                         String lastName,
                         String department,
                         int yearOfStudy,
                         double averageGrade,
                         List<String> skills,
                         List<String> interests,
                         String preferredLocation);

    Optional<Student> findByEmail(String email);
    void updateStudent(StudentDto dto, String email);

}