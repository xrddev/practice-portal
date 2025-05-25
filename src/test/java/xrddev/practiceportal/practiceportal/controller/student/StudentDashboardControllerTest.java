package xrddev.practiceportal.controller.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import xrddev.practiceportal.controller.student.StudentDashboardController;
import xrddev.practiceportal.dto.internship_assigment.InternshipAssignmentDashboardDto;
import xrddev.practiceportal.dto.internship_evaluations.StudentInternshipEvaluationDashboardDto;
import xrddev.practiceportal.dto.student.StudentDashboardDto;
import xrddev.practiceportal.service.internship_assigment.InternshipAssignmentService;
import xrddev.practiceportal.service.student.StudentService;

import java.security.Principal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentDashboardController.class)
class StudentDashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private InternshipAssignmentService internshipAssignmentService;

    private Principal principal;

    @BeforeEach
    void setup() {
        principal = () -> "student@example.com";
    }

    @Test
    @DisplayName("GET /student/dashboard shows dashboard view and model")
    void getDashboard_showsDashboard() throws Exception {
        // Προετοιμασία StudentDashboardDto
        StudentDashboardDto studentDto = new StudentDashboardDto();
        studentDto.setEmail("student@example.com");
        when(studentService.getByEmailMappedToDashboardDto(principal.getName()))
                .thenReturn(studentDto);

        // Προετοιμασία InternshipAssignmentDashboardDto
        InternshipAssignmentDashboardDto assignmentDto = new InternshipAssignmentDashboardDto();
        when(internshipAssignmentService.getByStudentEmailMappedToDashboardDto(principal.getName()))
                .thenReturn((InternshipAssignmentDashboardDto) List.of(assignmentDto));

        mockMvc.perform(get("/student/dashboard").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("student/dashboard"))
                .andExpect(model().attribute("student", studentDto))
                .andExpect(model().attribute("internship_assigment", List.of(assignmentDto)));
    }

    @Test
    @DisplayName("GET /student/evaluation shows evaluation form")
    void getEvaluation_showsForm() throws Exception {
        mockMvc.perform(get("/student/evaluation").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("student/evaluation"))
                .andExpect(model().attributeExists("evaluation"));
    }

    @Test
    @DisplayName("POST /student/evaluation on valid data redirects and saves")
    void postEvaluation_validData_redirectsAndSaves() throws Exception {
        mockMvc.perform(post("/student/evaluation")
                        .with(csrf())
                        .principal(principal)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/dashboard"));

        verify(internshipAssignmentService)
                .saveStudentEvaluation(eq(principal.getName()), any(StudentInternshipEvaluationDashboardDto.class));
    }
}