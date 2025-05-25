package xrddev.practiceportal.service.professor;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.professor.ProfessorEditDto;
import xrddev.practiceportal.dto.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.professor.Professor;
import xrddev.practiceportal.repository.ProfessorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceImplTest {

    @Mock
    private ProfessorRepository professorRepository;

    private ProfessorServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ProfessorServiceImpl(professorRepository);
    }

    @Test
    @DisplayName("registerProfessor: saves professor with encoded password")
    void registerProfessor_ShouldSaveProfessorWithEncodedPassword() {
        // Arrange
        ProfessorRegistrationDto dto = new ProfessorRegistrationDto();
        dto.setEmail("prof@uni.edu");
        dto.setPassword("secret");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setDepartment(xrddev.practiceportal.model.enums.Department.MATHEMATICS);
        dto.setInterests(Collections.emptyList());

        // Act
        service.registerProfessor(dto);

        // Assert
        ArgumentCaptor<Professor> captor = ArgumentCaptor.forClass(Professor.class);
        verify(professorRepository, times(1)).save(captor.capture());
        Professor saved = captor.getValue();

        assertThat(saved.getEmail()).isEqualTo(dto.getEmail());
        assertThat(saved.getRole()).isEqualTo(UserRole.PROFESSOR);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertThat(encoder.matches("secret", saved.getPassword())).isTrue();
        assertThat(saved.getFirstName()).isEqualTo("John");
        assertThat(saved.getLastName()).isEqualTo("Doe");
        assertThat(saved.getDepartment()).isEqualTo(dto.getDepartment());
        assertThat(saved.getInterests()).isEqualTo(dto.getInterests());
    }

    @Test
    @DisplayName("findByEmail: returns an Optional when professor exists")
    void findByEmail_WhenExists_ReturnsOptionalProfessor() {
        Professor prof = new Professor();
        prof.setEmail("a@b.com");
        when(professorRepository.findByEmail("a@b.com"))
            .thenReturn(Optional.of(prof));

        Optional<Professor> result = service.findByEmail("a@b.com");

        assertThat(result).isPresent().contains(prof);
        verify(professorRepository).findByEmail("a@b.com");
    }

    @Test
    @DisplayName("updateProfessor: updates fields and saves professor")
    void updateProfessor_WhenFound_UpdatesAndSaves() {
        String email = "prof@uni";
        Professor existing = new Professor();
        existing.setEmail(email);
        existing.setFirstName("Old");
        existing.setLastName("Name");
        existing.setInterests(Collections.emptyList());
        when(professorRepository.findByEmail(email))
            .thenReturn(Optional.of(existing));

        ProfessorEditDto dto = new ProfessorEditDto();
        dto.setFirstName("New");
        dto.setLastName("Person");
        dto.setInterests(Collections.emptyList());

        service.updateProfessor(dto, email);

        assertThat(existing.getFirstName()).isEqualTo("New");
        assertThat(existing.getLastName()).isEqualTo("Person");
        verify(professorRepository).save(existing);
    }

    @Test
    @DisplayName("updateProfessor: throws RuntimeException when professor not found")
    void updateProfessor_WhenNotFound_Throws() {
        when(professorRepository.findByEmail("no@one")).thenReturn(Optional.empty());
        ProfessorEditDto dto = new ProfessorEditDto();

        assertThrows(RuntimeException.class,
                     () -> service.updateProfessor(dto, "no@one"));
    }

    @Test
    @DisplayName("count: returns correct count")
    void count_ReturnsRepositoryCount() {
        when(professorRepository.count()).thenReturn(42L);
        assertThat(service.count()).isEqualTo(42L);
        verify(professorRepository).count();
    }

    @Test
    @DisplayName("getByEmailMappedToEditDto: returns DTO when professor exists")
    void getByEmailMappedToEditDto_WhenExists_ReturnsDto() {
        Professor prof = new Professor();
        prof.setEmail("e@u");
        prof.setFirstName("Fn");
        prof.setLastName("Ln");
        prof.setDepartment(xrddev.practiceportal.model.enums.Department.PHYSICS);
        prof.setInterests(Collections.emptyList());
        when(professorRepository.findByEmail("e@u"))
            .thenReturn(Optional.of(prof));

        ProfessorEditDto dto = service.getByEmailMappedToEditDto("e@u");
        assertThat(dto.getFirstName()).isEqualTo("Fn");
        assertThat(dto.getLastName()).isEqualTo("Ln");
    }

    @Test
    @DisplayName("getByEmailMappedToEditDto: throws EntityNotFoundException when professor not found")
    void getByEmailMappedToEditDto_WhenNotFound_Throws() {
        when(professorRepository.findByEmail("x")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                     () -> service.getByEmailMappedToEditDto("x"));
    }

    @Test
    @DisplayName("getByEmailMappedToDashboardDto: returns DashboardDto when professor exists")
    void getByEmailMappedToDashboardDto_WhenExists_ReturnsDto() {
        Professor prof = new Professor();
        prof.setId(5L);
        prof.setEmail("dash@u");
        prof.setFirstName("A");
        prof.setLastName("B");
        prof.setDepartment(xrddev.practiceportal.model.enums.Department.CHEMISTRY);
        prof.setInterests(Collections.emptyList());
        when(professorRepository.findByEmail("dash@u"))
            .thenReturn(Optional.of(prof));

        ProfessorDashboardDto dto = service.getByEmailMappedToDashboardDto("dash@u");
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getEmail()).isEqualTo("dash@u");
        assertThat(dto.getDepartment()).isEqualTo(prof.getDepartment());
    }

    @Test
    @DisplayName("getByEmailMappedToDashboardDto: throws EntityNotFoundException when professor not found")
    void getByEmailMappedToDashboardDto_WhenNotFound_Throws() {
        when(professorRepository.findByEmail("y")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                     () -> service.getByEmailMappedToDashboardDto("y"));
    }

    @Test
    @DisplayName("deleteById: calls repository.deleteById")
    void deleteById_CallsRepository() {
        service.deleteById(123L);
        verify(professorRepository).deleteById(123L);
    }

    @Test
    @DisplayName("getAllMappedToDashboardDto: maps all professors to DashboardDto")
    void getAllMappedToDashboardDto_ReturnsListOfDtos() {
        Professor p1 = new Professor();
        p1.setId(1L);
        Professor p2 = new Professor();
        p2.setId(2L);
        when(professorRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProfessorDashboardDto> dtos = service.getAllMappedToDashboardDto();
        assertThat(dtos).hasSize(2)
                        .extracting(ProfessorDashboardDto::getId)
                        .containsExactly(1L, 2L);
    }

    @Test
    @DisplayName("getAll: returns list of professors directly")
    void getAll_ReturnsListOfProfessors() {
        Professor p1 = new Professor();
        Professor p2 = new Professor();
        when(professorRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Professor> all = service.getAll();
        assertThat(all).containsExactly(p1, p2);
    }
}