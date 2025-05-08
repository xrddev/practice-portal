package xrddev.practiceportal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.Professor;
import xrddev.practiceportal.model.Student;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void registerStudent(String email,
                                String password,
                                String studentNumber,
                                String firstName,          // νέο
                                String lastName,           // νέο
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

    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}