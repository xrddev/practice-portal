package xrddev.practiceportal.service.internshipAssigment;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.*;
import xrddev.practiceportal.model.enums.Rating;
import xrddev.practiceportal.model.internship_assigment.InternshipAssignment;
import xrddev.practiceportal.model.internship_evaluations.*;
import xrddev.practiceportal.repository.InternshipAssignmentRepository;
import xrddev.practiceportal.repository.StudentInternshipEvaluationRepository;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InternshipAssignmentServiceImplTest {

    @Mock
    private InternshipAssignmentRepository assignmentRepository;

    @Mock
    private StudentInternshipEvaluationRepository studentEvalRepository;

    private InternshipAssignmentServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new InternshipAssignmentServiceImpl(assignmentRepository, studentEvalRepository);
    }

    @Test
    @DisplayName("getAllMappedToDashboardDto: returns list of DTOs")
    void getAllMappedToDashboardDto_ReturnsDtos() {
        InternshipAssignment a1 = new InternshipAssignment();
        a1.setId(1L);
        InternshipAssignment a2 = new InternshipAssignment();
        a2.setId(2L);
        when(assignmentRepository.findAll()).thenReturn(List.of(a1, a2));

        List<InternshipAssignmentDashboardDto> dtos = service.getAllMappedToDashboardDto();

        assertThat(dtos).hasSize(2)
                        .extracting(InternshipAssignmentDashboardDto::getId)
                        .containsExactly(1L, 2L);
        verify(assignmentRepository).findAll();
    }

    @Test
    @DisplayName("getByStudentEmailMappedToDashboardDto: returns DTO when found")
    void getByStudentEmailMappedToDashboardDto_WhenExists_ReturnsDto() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(5L);
        when(assignmentRepository.findByStudentEmail("stud@uni"))
            .thenReturn(Optional.of(a));

        InternshipAssignmentDashboardDto dto = service.getByStudentEmailMappedToDashboardDto("stud@uni");

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(5L);
        verify(assignmentRepository).findByStudentEmail("stud@uni");
    }

    @Test
    @DisplayName("getByStudentEmailMappedToDashboardDto: returns null when not found")
    void getByStudentEmailMappedToDashboardDto_WhenNotExists_ReturnsNull() {
        when(assignmentRepository.findByStudentEmail("none")).thenReturn(Optional.empty());

        InternshipAssignmentDashboardDto dto = service.getByStudentEmailMappedToDashboardDto("none");

        assertThat(dto).isNull();
    }

    @Test
    @DisplayName("deleteById: invokes repository delete")
    void deleteById_CallsRepository() {
        service.deleteById(10L);
        verify(assignmentRepository).deleteById(10L);
    }

    @Test
    @DisplayName("getAllByCompanyEmailMappedToDashboardDto: returns filtered DTOs")
    void getAllByCompanyEmailMappedToDashboardDto_ReturnsDtos() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(20L);
        when(assignmentRepository.findAllByPositionCompanyEmail("comp@co"))
            .thenReturn(List.of(a));

        List<InternshipAssignmentDashboardDto> dtos =
            service.getAllByCompanyEmailMappedToDashboardDto("comp@co");

        assertThat(dtos).hasSize(1).first().extracting(InternshipAssignmentDashboardDto::getId).isEqualTo(20L);
    }

    @Test
    @DisplayName("getAllByProfessorEmailMappedToDashboardDto: returns filtered DTOs")
    void getAllByProfessorEmailMappedToDashboardDto_ReturnsDtos() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(30L);
        when(assignmentRepository.findAllByProfessorEmail("prof@uni"))
            .thenReturn(List.of(a));

        List<InternshipAssignmentDashboardDto> dtos =
            service.getAllByProfessorEmailMappedToDashboardDto("prof@uni");

        assertThat(dtos).hasSize(1).first().extracting(InternshipAssignmentDashboardDto::getId).isEqualTo(30L);
    }

    @Test
    @DisplayName("saveStudentEvaluation: throws when assignment not found")
    void saveStudentEvaluation_WhenNotFound_Throws() {
        when(assignmentRepository.findByStudentEmail("x")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.saveStudentEvaluation("x", new StudentInternshipEvaluationDashboardDto()));
    }

    @Test
    @DisplayName("saveStudentEvaluation: sets new evaluation and saves")
    void saveStudentEvaluation_NewEvaluation_Saved() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(100L);
        when(assignmentRepository.findByStudentEmail("s@u"))
            .thenReturn(Optional.of(a));

        StudentInternshipEvaluationDashboardDto dto = new StudentInternshipEvaluationDashboardDto();
        dto.setRating(Rating.AVERAGE);
        dto.setComment("Good");

        service.saveStudentEvaluation("s@u", dto);

        ArgumentCaptor<InternshipAssignment> cap = ArgumentCaptor.forClass(InternshipAssignment.class);
        verify(assignmentRepository).save(cap.capture());
        InternshipAssignment saved = cap.getValue();

        assertThat(saved.getStudentEvaluation()).isNotNull();
        assertThat(saved.getStudentEvaluation().getRating()).isEqualTo(Rating.AVERAGE);
        assertThat(saved.getStudentEvaluation().getComment()).isEqualTo("Good");
    }

    @Test
    @DisplayName("saveStudentEvaluation: updates existing evaluation")
    void saveStudentEvaluation_ExistingEvaluation_Updated() {
        InternshipAssignment a = new InternshipAssignment();
        StudentInternshipEvaluation existing = new StudentInternshipEvaluation();
        a.setStudentEvaluation(existing);
        when(assignmentRepository.findByStudentEmail("s@u"))
            .thenReturn(Optional.of(a));

        StudentInternshipEvaluationDashboardDto dto = new StudentInternshipEvaluationDashboardDto();
        dto.setRating(Rating.POOR);
        dto.setComment("Poor");

        service.saveStudentEvaluation("s@u", dto);

        ArgumentCaptor<InternshipAssignment> cap = ArgumentCaptor.forClass(InternshipAssignment.class);
        verify(assignmentRepository).save(cap.capture());
        InternshipAssignment saved = cap.getValue();

        assertThat(saved.getStudentEvaluation()).isSameAs(existing);
        assertThat(existing.getRating()).isEqualTo(Rating.POOR);
        assertThat(existing.getComment()).isEqualTo("Poor");
    }

    @Test
    @DisplayName("saveCompanyEvaluation: throws when not found")
    void saveCompanyEvaluation_WhenNotFound_Throws() {
        when(assignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(1L, 2L, "c"))
            .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.saveCompanyEvaluation(1L, 2L, "c", new CompanyInternshipEvaluationDashboardDto()));
    }

    @Test
    @DisplayName("saveCompanyEvaluation: sets new evaluation and saves")
    void saveCompanyEvaluation_NewEvaluation_Saved() {
        InternshipAssignment a = new InternshipAssignment();
        when(assignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(5L, 6L, "c@co"))
            .thenReturn(Optional.of(a));

        CompanyInternshipEvaluationDashboardDto dto = new CompanyInternshipEvaluationDashboardDto();
        dto.setMotivation(Rating.EXCELLENT);
        dto.setEffectiveness(Rating.AVERAGE);
        dto.setEfficiency(Rating.GOOD);
        dto.setOverallGrade("6");
        dto.setComments("Ok");

        service.saveCompanyEvaluation(5L, 6L, "c@co", dto);

        ArgumentCaptor<InternshipAssignment> cap = ArgumentCaptor.forClass(InternshipAssignment.class);
        verify(assignmentRepository).save(cap.capture());
        InternshipAssignment saved = cap.getValue();

        CompanyInternshipEvaluation eval = saved.getCompanyEvaluation();
        assertThat(eval).isNotNull();
        assertThat(eval.getMotivation()).isEqualTo(Rating.EXCELLENT);
        assertThat(eval.getEffectiveness()).isEqualTo(Rating.AVERAGE);
        assertThat(eval.getEfficiency()).isEqualTo(Rating.GOOD);
        assertThat(eval.getOverallGrade()).isEqualTo("6");
        assertThat(eval.getComments()).isEqualTo("Ok");
    }


    @Test
    @DisplayName("getCombinedEvaluation: throws when not found")
    void getCombinedEvaluation_WhenNotFound_Throws() {
        when(assignmentRepository.findById(9L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.getCombinedEvaluation(9L));
    }

    @Test
    @DisplayName("getCombinedEvaluation: returns combined DTO")
    void getCombinedEvaluation_ReturnsDto() {
        InternshipAssignment a = new InternshipAssignment();
        StudentInternshipEvaluation se = new StudentInternshipEvaluation();
        se.setRating(Rating.EXCELLENT);
        se.setComment("Excellent");
        CompanyInternshipEvaluation ce = new CompanyInternshipEvaluation();
        ce.setComments("Cmt");
        ProfessorInternshipEvaluation pe = new ProfessorInternshipEvaluation();
        pe.setOverallGrade("7");
        a.setStudentEvaluation(se);
        a.setCompanyEvaluation(ce);
        a.setProfessorEvaluation(pe);
        when(assignmentRepository.findById(11L)).thenReturn(Optional.of(a));

        CombinedInternshipEvaluationDashboardDto dto = service.getCombinedEvaluation(11L);

        assertThat(dto.getStudentEvaluation().getRating()).isEqualTo(Rating.EXCELLENT);
        assertThat(dto.getCompanyEvaluation().getComments()).isEqualTo("Cmt");
        assertThat(dto.getProfessorEvaluation().getOverallGrade()).isEqualTo("7");
    }

    @Test
    @DisplayName("getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto: throws when not found")
    void getByByCompanyMapping_WhenNotFound_Throws() {
        when(assignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(1L, 2L, "c"))
            .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto(1L, 2L, "c"));
    }

    @Test
    @DisplayName("getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto: returns DTO when found")
    void getByByCompanyMapping_WhenFound_ReturnsDto() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(13L);
        when(assignmentRepository.findByIdAndStudentIdAndPositionCompanyEmail(13L, 14L, "c@co"))
            .thenReturn(Optional.of(a));

        InternshipAssignmentDashboardDto dto =
            service.getByAssigmentIDAndStudentIDAndPositionCompanyEmailMappedToDashboardDto(13L, 14L, "c@co");

        assertThat(dto.getId()).isEqualTo(13L);
    }

    @Test
    @DisplayName("getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto: throws when not found")
    void getByByProfessorMapping_WhenNotFound_Throws() {
        when(assignmentRepository.findByIdAndStudentIdAndProfessorEmail(2L, 3L, "p"))
            .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> service.getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto(2L, 3L, "p"));
    }

    @Test
    @DisplayName("getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto: returns DTO when found")
    void getByByProfessorMapping_WhenFound_ReturnsDto() {
        InternshipAssignment a = new InternshipAssignment();
        a.setId(15L);
        when(assignmentRepository.findByIdAndStudentIdAndProfessorEmail(15L, 16L, "p@uni"))
            .thenReturn(Optional.of(a));

        InternshipAssignmentDashboardDto dto =
            service.getByAssigmentIDAndStudentIDAndProfessorEmailMappedToDashboardDto(15L, 16L, "p@uni");

        assertThat(dto.getId()).isEqualTo(15L);
    }
}