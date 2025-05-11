package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.student.StudentDto;
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
    public void registerStudent(String email,
                                String password,
                                String studentNumber,
                                String firstName,
                                String lastName,
                                String department,
                                int yearOfStudy,
                                double averageGrade,
                                List<String> skills,
                                List<String> interests,
                                String preferredLocation) {

        Student student = new Student();
        student.setRole(UserRole.STUDENT);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPassword(password);
        student.setStudentNumber(studentNumber);
        student.setDepartment(Department.valueOf(department));
        student.setYearOfStudy(yearOfStudy);
        student.setAverageGrade(averageGrade);
        student.setSkills(skills.stream().map(Skills::valueOf).toList());
        student.setInterests(interests.stream().map(Interests::valueOf).toList());
        student.setPreferredLocation(preferredLocation);

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