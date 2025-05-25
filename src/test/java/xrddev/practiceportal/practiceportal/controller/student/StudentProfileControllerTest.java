package xrddev.practiceportal.controller.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import xrddev.practiceportal.dto.student.StudentEditDto;
import xrddev.practiceportal.service.student.StudentService;

import java.security.Principal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentProfileController.class)
class StudentProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Principal principal;

    @BeforeEach
    void setup() {
        principal = () -> "student@example.com";
    }

    @Test
    @DisplayName("GET /student/edit-profile returns edit form and model")
    void getEditProfile_showsForm() throws Exception {
        StudentEditDto dto = new StudentEditDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        when(studentService.getByEmailMappedToEditDto(principal.getName()))
            .thenReturn(dto);

        mockMvc.perform(get("/student/edit-profile").principal(principal))
            .andExpect(status().isOk())
            .andExpect(view().name("student/edit_profile"))
            .andExpect(model().attribute("student", dto));
    }

    @Test
    @DisplayName("POST /student/edit-profile on valid data redirects and updates")
    void postEditProfile_validData_redirectsAndUpdates() throws Exception {
        mockMvc.perform(post("/student/edit-profile")
                .with(csrf())
                .principal(principal)
                .param("firstName", "Alice")
                .param("lastName", "Smith")
                .param("yearOfStudy", "4")
                .param("averageGrade", "9.2")
                .param("preferredLocation", "Athens")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/student/dashboard"));

        ArgumentCaptor<StudentEditDto> captor = ArgumentCaptor.forClass(StudentEditDto.class);
        verify(studentService).updateStudent(captor.capture(), eq(principal.getName()));
        StudentEditDto sent = captor.getValue();
        assertThat(sent.getFirstName()).isEqualTo("Alice");
        assertThat(sent.getLastName()).isEqualTo("Smith");
        assertThat(sent.getYearOfStudy()).isEqualTo(4);
        assertThat(sent.getAverageGrade()).isEqualTo(9.2);
        assertThat(sent.getPreferredLocation()).isEqualTo("Athens");
    }

    @Test
    @DisplayName("POST /student/edit-profile on missing fields returns to form")
    void postEditProfile_invalidData_returnsForm() throws Exception {
        mockMvc.perform(post("/student/edit-profile")
                .with(csrf())
                .principal(principal)
                .param("firstName", "OnlyOneField")
                // no lastName etc.
        )
        .andExpect(status().isOk())
        .andExpect(view().name("student/edit_profile"));

        verify(studentService, never()).updateStudent(any(), anyString());
    }
}