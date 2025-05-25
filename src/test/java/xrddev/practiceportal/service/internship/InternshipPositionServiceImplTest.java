package xrddev.practiceportal.service.internship;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xrddev.practiceportal.dto.intership_position.*;
import xrddev.practiceportal.model.company.Company;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.model.internship_position.InternshipPosition;
import xrddev.practiceportal.repository.InternshipPositionRepository;
import xrddev.practiceportal.service.company.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InternshipPositionServiceImplTest {

    @Mock
    private InternshipPositionRepository positionRepository;

    @Mock
    private CompanyService companyService;

    private InternshipPositionServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new InternshipPositionServiceImpl(positionRepository, companyService);
    }

    @Test
    @DisplayName("createPosition: throws if company not found")
    void createPosition_whenCompanyMissing_throws() {
        InternshipPositionCreateDto dto = new InternshipPositionCreateDto();
        dto.setTitle("T");
        dto.setDescription("D");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusDays(1));
        dto.setSkills(List.of());
        dto.setInterests(List.of());

        when(companyService.findByEmail("no@co")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> service.createPosition(dto, "no@co"));
        verify(positionRepository, never()).save(any());
    }

    @Test
    @DisplayName("createPosition: saves new position with correct fields")
    void createPosition_savesNewPosition() {
        var start = LocalDate.of(2024, 1, 1);
        var end = LocalDate.of(2024, 6, 1);
        var skills = List.of(Skills.JAVA, Skills.PYTHON);
        var interests = List.of(Interests.DATA_SCIENCE);

        InternshipPositionCreateDto dto = new InternshipPositionCreateDto();
        dto.setTitle("Title");
        dto.setDescription("Desc");
        dto.setStartDate(start);
        dto.setEndDate(end);
        dto.setSkills(skills);
        dto.setInterests(interests);

        Company company = new Company();
        company.setId(42L);
        company.setEmail("co@ex");
        when(companyService.findByEmail("co@ex")).thenReturn(Optional.of(company));

        service.createPosition(dto, "co@ex");

        ArgumentCaptor<InternshipPosition> cap = ArgumentCaptor.forClass(InternshipPosition.class);
        verify(positionRepository).save(cap.capture());
        InternshipPosition saved = cap.getValue();

        assertThat(saved.getTitle()).isEqualTo("Title");
        assertThat(saved.getDescription()).isEqualTo("Desc");
        assertThat(saved.getStartDate()).isEqualTo(start);
        assertThat(saved.getEndDate()).isEqualTo(end);
        assertThat(saved.getSkills()).containsExactlyElementsOf(skills);
        assertThat(saved.getInterests()).containsExactlyElementsOf(interests);
        assertThat(saved.getCompany()).isSameAs(company);
    }

    @Test
    @DisplayName("updatePosition: throws if not found or not owner")
    void updatePosition_whenMissing_throws() {
        InternshipPositionEditDto edit = new InternshipPositionEditDto();
        edit.setId(1L);
        edit.setTitle("T");
        edit.setDescription("D");
        edit.setStartDate(LocalDate.now());
        edit.setEndDate(LocalDate.now());
        edit.setSkills(List.of());
        edit.setInterests(List.of());

        when(positionRepository.findByIdAndCompanyEmail(1L, "x@co")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> service.updatePosition(1L, "x@co", edit));
    }

    @Test
    @DisplayName("updatePosition: updates and saves entity")
    void updatePosition_updatesAndSaves() {
        InternshipPosition pos = new InternshipPosition();
        pos.setTitle("Old");
        when(positionRepository.findByIdAndCompanyEmail(5L, "co@ex"))
                .thenReturn(Optional.of(pos));

        var newStart = LocalDate.of(2025, 2, 2);
        var newEnd = LocalDate.of(2025, 8, 2);

        InternshipPositionEditDto edit = new InternshipPositionEditDto();
        edit.setId(5L);
        edit.setTitle("New");
        edit.setDescription("Desc2");
        edit.setStartDate(newStart);
        edit.setEndDate(newEnd);
        edit.setSkills(List.of(Skills.MEDICAL_CARE));
        edit.setInterests(List.of(Interests.LITERATURE));

        service.updatePosition(5L, "co@ex", edit);

        ArgumentCaptor<InternshipPosition> cap = ArgumentCaptor.forClass(InternshipPosition.class);
        verify(positionRepository).save(cap.capture());
        InternshipPosition updated = cap.getValue();

        assertThat(updated.getTitle()).isEqualTo("New");
        assertThat(updated.getDescription()).isEqualTo("Desc2");
        assertThat(updated.getStartDate()).isEqualTo(newStart);
        assertThat(updated.getEndDate()).isEqualTo(newEnd);
        assertThat(updated.getSkills()).containsExactly(Skills.MEDICAL_CARE);
        assertThat(updated.getInterests()).containsExactly(Interests.LITERATURE);
    }


    @Test
    @DisplayName("deleteByIdAndCompanyEmail: throws if not found")
    void deleteByIdAndCompanyEmail_whenMissing_throws() {
        when(positionRepository.findByIdAndCompanyEmail(2L, "a@b")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.deleteByIdAndCompanyEmail(2L, "a@b"));
    }

    @Test
    @DisplayName("deleteByIdAndCompanyEmail: deletes found entity")
    void deleteByIdAndCompanyEmail_deletesEntity() {
        InternshipPosition pos = new InternshipPosition();
        when(positionRepository.findByIdAndCompanyEmail(3L, "co@ex"))
            .thenReturn(Optional.of(pos));

        service.deleteByIdAndCompanyEmail(3L, "co@ex");
        verify(positionRepository).delete(pos);
    }

    @Test
    @DisplayName("count: returns repository count")
    void count_returnsCorrect() {
        when(positionRepository.count()).thenReturn(7L);
        assertThat(service.count()).isEqualTo(7L);
    }

    @Test
    @DisplayName("getByIdAndCompanyEmailMappedToEditDto: throws if not found")
    void getByIdAndCompanyEmailMappedToEditDto_whenMissing_throws() {
        when(positionRepository.findByIdAndCompanyEmail(4L, "c@d")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
            () -> service.getByIdAndCompanyEmailMappedToEditDto(4L, "c@d"));
    }

    @Test
    @DisplayName("getByIdAndCompanyEmailMappedToEditDto: returns DTO when found")
    void getByIdAndCompanyEmailMappedToEditDto_returnsDto() {
        InternshipPosition pos = new InternshipPosition();
        pos.setId(8L);
        pos.setTitle("T1");
        when(positionRepository.findByIdAndCompanyEmail(8L, "co@ex"))
            .thenReturn(Optional.of(pos));

        var dto = service.getByIdAndCompanyEmailMappedToEditDto(8L, "co@ex");
        assertThat(dto.getId()).isEqualTo(8L);
        assertThat(dto.getTitle()).isEqualTo("T1");
    }

    @Test
    @DisplayName("getAllByCompanyEmailMappedToDashboardDto: returns proper list")
    void getAllByCompanyEmailMappedToDashboardDto_returnsList() {
        InternshipPosition p = new InternshipPosition();
        p.setId(9L);
        when(positionRepository.findAllByCompanyEmail("co@ex"))
            .thenReturn(List.of(p));

        var list = service.getAllByCompanyEmailMappedToDashboardDto("co@ex");
        assertThat(list).hasSize(1)
                        .first().extracting(InternshipPositionDashboardDto::getId)
                        .isEqualTo(9L);
    }

    @Test
    @DisplayName("getByIdAndCompanyEmailMappedToDashboardDto: throws if not found")
    void getByIdAndCompanyEmailMappedToDashboardDto_whenMissing_throws() {
        when(positionRepository.findByIdAndCompanyEmail(10L, "x@y")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
            () -> service.getByIdAndCompanyEmailMappedToDashboardDto(10L, "x@y"));
    }

    @Test
    @DisplayName("getByIdAndCompanyEmailMappedToDashboardDto: returns DTO when found")
    void getByIdAndCompanyEmailMappedToDashboardDto_returnsDto() {
        InternshipPosition p = new InternshipPosition();
        p.setId(11L);
        when(positionRepository.findByIdAndCompanyEmail(11L, "co@ex"))
            .thenReturn(Optional.of(p));

        var dto = service.getByIdAndCompanyEmailMappedToDashboardDto(11L, "co@ex");
        assertThat(dto.getId()).isEqualTo(11L);
    }

    @Test
    @DisplayName("getAllMappedToDashboardDto: returns all mapped")
    void getAllMappedToDashboardDto_returnsAll() {
        InternshipPosition a = new InternshipPosition(); a.setId(1L);
        InternshipPosition b = new InternshipPosition(); b.setId(2L);
        when(positionRepository.findAll()).thenReturn(List.of(a, b));

        var dtos = service.getAllMappedToDashboardDto();
        assertThat(dtos).hasSize(2)
                        .extracting(InternshipPositionDashboardDto::getId)
                        .containsExactly(1L, 2L);
    }

    @Test
    @DisplayName("getAllByCompanyIdMappedToDashboardDto: returns filtered by companyId")
    void getAllByCompanyIdMappedToDashboardDto_returnsList() {
        InternshipPosition p = new InternshipPosition();
        p.setId(12L);
        when(positionRepository.findAllByCompanyId(100L))
            .thenReturn(List.of(p));

        var list = service.getAllByCompanyIdMappedToDashboardDto(100L);
        assertThat(list).hasSize(1)
                        .first().extracting(InternshipPositionDashboardDto::getId)
                        .isEqualTo(12L);
    }

    @Test
    @DisplayName("deleteById: invokes repository")
    void deleteById_invokesRepository() {
        service.deleteById(99L);
        verify(positionRepository).deleteById(99L);
    }

    @Test
    @DisplayName("getAll: returns repository list")
    void getAll_returnsList() {
        InternshipPosition p1 = new InternshipPosition(); p1.setId(21L);
        when(positionRepository.findAll()).thenReturn(List.of(p1));

        var list = service.getAll();
        assertThat(list).hasSize(1).first().isSameAs(p1);
    }
}