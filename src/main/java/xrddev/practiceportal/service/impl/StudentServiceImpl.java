package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.user.student.StudentDto;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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
    public void updateStudent(StudentDto dto, String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setStudentNumber(dto.getStudentNumber());
        student.setYearOfStudy(dto.getYearOfStudy());
        student.setAverageGrade(dto.getAverageGrade());
        student.setInterests(dto.getInterests());
        student.setSkills(dto.getSkills());
        student.setPreferredLocation(dto.getPreferredLocation());

        studentRepository.save(student);
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
    public List<StudentDto> getAllMappedToDto() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDto::new)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getByEmailMappedToDto(String email) {
        return studentRepository.findByEmail(email)
                .map(StudentDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

}