package xrddev.practiceportal.service.student;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import xrddev.practiceportal.dto.student.StudentDashboardDto;
import xrddev.practiceportal.dto.student.StudentEditDto;
import xrddev.practiceportal.dto.student.StudentRegistrationDto;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.student.Student;
import xrddev.practiceportal.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Captor
    private ArgumentCaptor<Student> studentCaptor;

    private StudentRegistrationDto dto;

    @BeforeEach
    void setUp() {
        dto = new StudentRegistrationDto();
        dto.setEmail("cs04351@uoi.gr");
        dto.setPassword("plainPass");
        dto.setStudentNumber("184351");
        dto.setFirstName("Christos");
        dto.setLastName("Dimitresis");
        dto.setDepartment(Department.AGRICULTURE);
        dto.setYearOfStudy(7);
        dto.setAverageGrade(9.2);
        dto.setSkills(List.of(Skills.JAVA, Skills.PYTHON, Skills.PROGRAMMING));
        dto.setInterests(List.of(Interests.ASTRONOMY, Interests.HISTORY));
        dto.setPreferredLocation("Athens");
    }

    @Test
    @DisplayName("registerStudent: encrypts password, sets all fields and saves Student")
    void registerStudent_ShouldEncodePasswordAndSaveAllFields() {
        when(passwordEncoder.encode("plainPass")).thenReturn("encodedPass123");

        studentService.registerStudent(dto);

        verify(passwordEncoder).encode("plainPass");
        verify(studentRepository).save(studentCaptor.capture());

        Student saved = studentCaptor.getValue();
        assertThat(saved.getEmail()).isEqualTo("cs04351@uoi.gr");
        assertThat(saved.getPassword()).isEqualTo("encodedPass123");
        assertThat(saved.getRole()).isEqualTo(UserRole.STUDENT);
        assertThat(saved.getStudentNumber()).isEqualTo("184351");
        assertThat(saved.getFirstName()).isEqualTo("Christos");
        assertThat(saved.getLastName()).isEqualTo("Dimitresis");
        assertThat(saved.getDepartment()).isEqualTo(Department.AGRICULTURE);
        assertThat(saved.getYearOfStudy()).isEqualTo(7);
        assertThat(saved.getAverageGrade()).isEqualTo(9.2);
        assertThat(saved.getSkills()).containsExactlyInAnyOrder(
                Skills.JAVA, Skills.PYTHON, Skills.PROGRAMMING
        );
        assertThat(saved.getInterests()).containsExactlyInAnyOrder(
                Interests.ASTRONOMY, Interests.HISTORY
        );
        assertThat(saved.getPreferredLocation()).isEqualTo("Athens");
    }

    @Test
    @DisplayName("updateStudent: updates fields and saves Student")
    void updateStudent_ShouldUpdateFieldsAndSave() {
        Student existing = new Student();
        existing.setEmail("s@uoi.gr");
        existing.setFirstName("Old");
        existing.setLastName("Name");
        when(studentRepository.findByEmail("s@uoi.gr"))
                .thenReturn(Optional.of(existing));

        StudentEditDto edit = new StudentEditDto();
        edit.setFirstName("New");
        edit.setLastName("Changed");
        edit.setYearOfStudy(3);
        edit.setAverageGrade(8.5);
        edit.setSkills(List.of(Skills.JAVA));
        edit.setInterests(List.of(Interests.ASTRONOMY));
        edit.setPreferredLocation("Thessaloniki");

        studentService.updateStudent(edit, "s@uoi.gr");

        verify(studentRepository).save(studentCaptor.capture());
        Student saved = studentCaptor.getValue();
        assertThat(saved.getFirstName()).isEqualTo("New");
        assertThat(saved.getLastName()).isEqualTo("Changed");
        assertThat(saved.getYearOfStudy()).isEqualTo(3);
        assertThat(saved.getAverageGrade()).isEqualTo(8.5);
        assertThat(saved.getSkills()).containsExactly(Skills.JAVA);
        assertThat(saved.getInterests()).containsExactly(Interests.ASTRONOMY);
        assertThat(saved.getPreferredLocation()).isEqualTo("Thessaloniki");
    }

    @Test
    @DisplayName("updateStudent: throws exception if student not found")
    void updateStudent_WhenNotFound_ShouldThrow() {
        when(studentRepository.findByEmail("no@uoi.gr")).thenReturn(Optional.empty());
        StudentEditDto edit = new StudentEditDto();
        assertThatThrownBy(() ->
                studentService.updateStudent(edit, "no@uoi.gr")
        ).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    @DisplayName("getAllMappedToDashboardDto: maps all correctly to DTOs")
    void getAllMappedToDashboardDto_ShouldMapAll() {
        Student s1 = new Student();
        s1.setId(1L);
        s1.setEmail("a@uoi.gr");
        s1.setFirstName("A");
        s1.setLastName("One");
        Student s2 = new Student();
        s2.setId(2L);
        s2.setEmail("b@uoi.gr");
        s2.setFirstName("B");
        s2.setLastName("Two");
        when(studentRepository.findAll()).thenReturn(List.of(s1, s2));

        List<StudentDashboardDto> dtos = studentService.getAllMappedToDashboardDto();

        assertThat(dtos).hasSize(2);
        assertThat(dtos).extracting("email")
                .containsExactlyInAnyOrder("a@uoi.gr", "b@uoi.gr");
    }

    @Test
    @DisplayName("findByEmail: returns Optional<Student> when present / empty when absent")
    void findByEmail_ShouldReturnOptional() {
        Student s = new Student();
        when(studentRepository.findByEmail("x@uoi.gr"))
                .thenReturn(Optional.of(s));
        assertThat(studentService.findByEmail("x@uoi.gr")).isPresent();

        when(studentRepository.findByEmail("y@uoi.gr"))
                .thenReturn(Optional.empty());
        assertThat(studentService.findByEmail("y@uoi.gr")).isNotPresent();
    }

    @Test
    @DisplayName("count: returns the count from repository")
    void count_ShouldReturnRepositoryCount() {
        when(studentRepository.count()).thenReturn(42L);
        assertThat(studentService.count()).isEqualTo(42L);
    }

    @Test
    @DisplayName("deleteById: calls repository.deleteById")
    void deleteById_ShouldCallRepository() {
        studentService.deleteById(7L);
        verify(studentRepository).deleteById(7L);
    }

    @Test
    @DisplayName("getByEmailMappedToDashboardDto: success and EntityNotFoundException")
    void getByEmailMappedToDashboardDto_SuccessAndFailure() {
        Student s = new Student();
        s.setId(1L);
        s.setEmail("z@uoi.gr");
        when(studentRepository.findByEmail("z@uoi.gr"))
                .thenReturn(Optional.of(s));
        StudentDashboardDto dto = studentService.getByEmailMappedToDashboardDto("z@uoi.gr");
        assertThat(dto.getEmail()).isEqualTo("z@uoi.gr");

        when(studentRepository.findByEmail("nope@uoi.gr"))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() ->
                studentService.getByEmailMappedToDashboardDto("nope@uoi.gr")
        ).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("findById: Optional<Student> when present / empty when absent")
    void findById_ShouldReturnOptional() {
        Student s = new Student();
        when(studentRepository.findById(13L))
                .thenReturn(Optional.of(s));
        assertThat(studentService.findById(13L)).isPresent();

        when(studentRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThat(studentService.findById(99L)).isNotPresent();
    }

    @Test
    @DisplayName("getAll: returns all students")
    void getAll_ShouldReturnAllStudents() {
        Student s1 = new Student();
        Student s2 = new Student();
        when(studentRepository.findAll()).thenReturn(List.of(s1, s2));
        List<Student> all = studentService.getAll();
        assertThat(all).containsExactly(s1, s2);
    }

}