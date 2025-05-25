package xrddev.practiceportal.service.practice_office;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.practice_office.PracticeOfficeAdmin;
import xrddev.practiceportal.repository.PracticeOfficeAdminRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PracticeOfficeAdminServiceImplTest {

    @Mock
    private PracticeOfficeAdminRepository repository;

    private PracticeOfficeAdminServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PracticeOfficeAdminServiceImpl(repository);
    }

    @Test
    @DisplayName("registerAdmin: saves admin with correct role and fields")
    void registerAdmin_SavesAdminWithCorrectValues() {
        PracticeOfficeAdminDto dto = new PracticeOfficeAdminDto();
        dto.setEmail("admin@office");
        dto.setPassword("pass123");

        service.registerAdmin(dto);

        ArgumentCaptor<PracticeOfficeAdmin> captor = ArgumentCaptor.forClass(PracticeOfficeAdmin.class);
        verify(repository, times(1)).save(captor.capture());
        PracticeOfficeAdmin saved = captor.getValue();

        assertThat(saved.getEmail()).isEqualTo(dto.getEmail());
        assertThat(saved.getPassword()).isEqualTo(dto.getPassword());
        assertThat(saved.getRole()).isEqualTo(UserRole.PRACTICE_OFFICE);
    }

    @Test
    @DisplayName("findByEmail: returns Optional.of when admin exists")
    void findByEmail_WhenExists_ReturnsOptional() {
        PracticeOfficeAdmin admin = new PracticeOfficeAdmin();
        admin.setEmail("user@office");
        when(repository.findByEmail("user@office"))
            .thenReturn(Optional.of(admin));

        Optional<PracticeOfficeAdmin> result = service.findByEmail("user@office");

        assertThat(result).isPresent().contains(admin);
        verify(repository).findByEmail("user@office");
    }

    @Test
    @DisplayName("findByEmail: returns empty Optional when admin not found")
    void findByEmail_WhenNotExists_ReturnsEmpty() {
        when(repository.findByEmail("none")).thenReturn(Optional.empty());

        Optional<PracticeOfficeAdmin> result = service.findByEmail("none");

        assertThat(result).isEmpty();
        verify(repository).findByEmail("none");
    }

    @Test
    @DisplayName("getByEmailMappedToDto: returns DTO when admin exists")
    void getByEmailMappedToDto_WhenExists_ReturnsDto() {
        PracticeOfficeAdmin admin = new PracticeOfficeAdmin();
        admin.setEmail("dto@office");
        admin.setPassword("pwd");
        when(repository.findByEmail("dto@office"))
            .thenReturn(Optional.of(admin));

        PracticeOfficeAdminDto dto = service.getByEmailMappedToDto("dto@office");

        assertThat(dto.getEmail()).isEqualTo("dto@office");
    }

    @Test
    @DisplayName("getByEmailMappedToDto: throws when admin not found")
    void getByEmailMappedToDto_WhenNotExists_ThrowsException() {
        when(repository.findByEmail("missing")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                     () -> service.getByEmailMappedToDto("missing"));
    }
}