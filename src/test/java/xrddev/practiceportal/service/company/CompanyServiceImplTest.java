package xrddev.practiceportal.service.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.dto.company.CompanyEditDto;
import xrddev.practiceportal.dto.company.CompanyRegistrationDto;
import xrddev.practiceportal.model.company.Company;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CompanyServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CompanyServiceImpl(companyRepository, passwordEncoder);
    }

    @Test
    @DisplayName("registerCompany: saves new company with encrypted password")
    void registerCompany_savesNewCompany() {
        CompanyRegistrationDto dto = new CompanyRegistrationDto();
        dto.setEmail("test@co");
        dto.setPassword("plainPass");
        dto.setCompanyName("TestCo");
        dto.setAddress("123 Main St");
        dto.setPhone("555-1234");
        dto.setWebsite("https://test.co");
        dto.setInternshipCoordinator("John Doe");
        dto.setInternshipCoordinatorEmail("john@test.co");

        when(passwordEncoder.encode("plainPass")).thenReturn("encPass");

        service.registerCompany(dto);

        ArgumentCaptor<Company> cap = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository).save(cap.capture());
        Company saved = cap.getValue();

        assertThat(saved.getEmail()).isEqualTo("test@co");
        assertThat(saved.getPassword()).isEqualTo("encPass");
        assertThat(saved.getRole()).isEqualTo(UserRole.COMPANY);
        assertThat(saved.getCompanyName()).isEqualTo("TestCo");
        assertThat(saved.getAddress()).isEqualTo("123 Main St");
        assertThat(saved.getPhone()).isEqualTo("555-1234");
        assertThat(saved.getWebsite()).isEqualTo("https://test.co");
        assertThat(saved.getInternshipCoordinator()).isEqualTo("John Doe");
        assertThat(saved.getInternshipCoordinatorEmail()).isEqualTo("john@test.co");
    }

    @Test
    @DisplayName("findByEmail: delegates to repository")
    void findByEmail_delegatesToRepository() {
        Company c = new Company();
        when(companyRepository.findByEmail("a@b")).thenReturn(Optional.of(c));

        Optional<Company> result = service.findByEmail("a@b");

        assertThat(result).containsSame(c);
    }

    @Test
    @DisplayName("getByEmailMappedToDashboardDto: returns dto when found")
    void getByEmailMappedToDashboardDto_returnsDto() {
        Company c = new Company();
        c.setId(7L);
        c.setEmail("e@f");
        c.setCompanyName("CoName");
        when(companyRepository.findByEmail("e@f")).thenReturn(Optional.of(c));

        CompanyDashboardDto dto = service.getByEmailMappedToDashboardDto("e@f");

        assertThat(dto.getId()).isEqualTo(7L);
        assertThat(dto.getEmail()).isEqualTo("e@f");
        assertThat(dto.getCompanyName()).isEqualTo("CoName");
    }

    @Test
    @DisplayName("getByEmailMappedToDashboardDto: throws when not found")
    void getByEmailMappedToDashboardDto_throwsIfMissing() {
        when(companyRepository.findByEmail("x@y")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
            () -> service.getByEmailMappedToDashboardDto("x@y"));
    }

    @Test
    @DisplayName("getByEmailMappedToEditDto: returns dto when found")
    void getByEmailMappedToEditDto_returnsDto() {
        Company c = new Company();
        c.setEmail("g@h");
        c.setCompanyName("OldName");
        when(companyRepository.findByEmail("g@h")).thenReturn(Optional.of(c));

        CompanyEditDto dto = service.getByEmailMappedToEditDto("g@h");

        assertThat(dto.getCompanyName()).isEqualTo("OldName");
        assertThat(dto.getAddress()).isEqualTo(c.getAddress());
    }

    @Test
    @DisplayName("getByEmailMappedToEditDto: throws when not found")
    void getByEmailMappedToEditDto_throwsIfMissing() {
        when(companyRepository.findByEmail("z@w")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
            () -> service.getByEmailMappedToEditDto("z@w"));
    }

    @Test
    @DisplayName("updateCompany: throws if company missing")
    void updateCompany_throwsIfMissing() {
        CompanyEditDto dto = new CompanyEditDto();
        dto.setCompanyName("X");
        when(companyRepository.findByEmail("no@co")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
            () -> service.updateCompany(dto, "no@co"));
        verify(companyRepository, never()).save(any());
    }

    @Test
    @DisplayName("updateCompany: updates fields and saves")
    void updateCompany_updatesAndSaves() {
        Company existing = new Company();
        existing.setEmail("u@v");
        existing.setCompanyName("OldCo");
        when(companyRepository.findByEmail("u@v")).thenReturn(Optional.of(existing));

        CompanyEditDto dto = new CompanyEditDto();
        dto.setCompanyName("NewCo");
        dto.setAddress("Addr");
        dto.setPhone("999");
        dto.setWebsite("site");
        dto.setInternshipCoordinator("Coord");
        dto.setInternshipCoordinatorEmail("coord@u");

        service.updateCompany(dto, "u@v");

        ArgumentCaptor<Company> cap = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository).save(cap.capture());
        Company updated = cap.getValue();

        assertThat(updated.getCompanyName()).isEqualTo("NewCo");
        assertThat(updated.getAddress()).isEqualTo("Addr");
        assertThat(updated.getPhone()).isEqualTo("999");
        assertThat(updated.getWebsite()).isEqualTo("site");
        assertThat(updated.getInternshipCoordinator()).isEqualTo("Coord");
        assertThat(updated.getInternshipCoordinatorEmail()).isEqualTo("coord@u");
    }

    @Test
    @DisplayName("count: delegates to repository")
    void count_delegatesToRepository() {
        when(companyRepository.count()).thenReturn(5L);
        assertThat(service.count()).isEqualTo(5L);
    }

    @Test
    @DisplayName("getAllMappedToDashboardDto: returns list of dtos")
    void getAllMappedToDashboardDto_returnsList() {
        Company c1 = new Company(); c1.setId(1L); c1.setEmail("a@a");
        Company c2 = new Company(); c2.setId(2L); c2.setEmail("b@b");
        when(companyRepository.findAll()).thenReturn(List.of(c1, c2));

        var dtos = service.getAllMappedToDashboardDto();

        assertThat(dtos).hasSize(2)
            .extracting(CompanyDashboardDto::getId, CompanyDashboardDto::getEmail)
            .containsExactly(tuple(1L, "a@a"), tuple(2L, "b@b"));
    }

    @Test
    @DisplayName("deleteById: delegates to repository")
    void deleteById_delegatesToRepository() {
        service.deleteById(42L);
        verify(companyRepository).deleteById(42L);
    }

    @Test
    @DisplayName("getById: returns entity when found")
    void getById_returnsEntity() {
        Company c = new Company();
        when(companyRepository.findById(9L)).thenReturn(Optional.of(c));
        assertThat(service.getById(9L)).isSameAs(c);
    }

    @Test
    @DisplayName("getById: throws when not found")
    void getById_throwsIfMissing() {
        when(companyRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
            () -> service.getById(99L));
    }
}