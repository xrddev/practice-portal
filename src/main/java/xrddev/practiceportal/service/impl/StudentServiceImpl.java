package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.user.student.StudentDashboardDto;
import xrddev.practiceportal.dto.user.student.StudentEditDto;
import xrddev.practiceportal.dto.user.student.StudentRegistrationDto;
import xrddev.practiceportal.model.user.Student;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.api.StudentRepository;
import xrddev.practiceportal.service.api.StudentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void registerStudent(StudentRegistrationDto studentRegistrationDto) {
        Student student = new Student();

        student.setEmail(studentRegistrationDto.getEmail());
        student.setPassword(studentRegistrationDto.getPassword());
        student.setRole(UserRole.STUDENT);

        student.setStudentNumber(studentRegistrationDto.getStudentNumber());
        student.setFirstName(studentRegistrationDto.getFirstName());
        student.setLastName(studentRegistrationDto.getLastName());
        student.setDepartment(studentRegistrationDto.getDepartment());
        student.setYearOfStudy(studentRegistrationDto.getYearOfStudy());
        student.setAverageGrade(studentRegistrationDto.getAverageGrade());
        student.setSkills(studentRegistrationDto.getSkills());
        student.setInterests(studentRegistrationDto.getInterests());
        student.setPreferredLocation(studentRegistrationDto.getPreferredLocation());

        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentEditDto dto, String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setYearOfStudy(dto.getYearOfStudy());
        student.setAverageGrade(dto.getAverageGrade());
        student.setSkills(dto.getSkills());
        student.setInterests(dto.getInterests());
        student.setPreferredLocation(dto.getPreferredLocation());
        studentRepository.save(student);
    }



    @Override
    public List<StudentDashboardDto> getAllMappedToDashboardDto() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDashboardDto::new)
                .toList();
    }






    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }


    @Override
    public long count() {
        return studentRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDashboardDto getByEmailMappedToDashboardDto(String email) {
        return studentRepository.findByEmail(email)
                .map(StudentDashboardDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    @Override
    public StudentEditDto getByEmailMappedToEditDto(String email) {
        return studentRepository.findByEmail(email)
                .map(StudentEditDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

}