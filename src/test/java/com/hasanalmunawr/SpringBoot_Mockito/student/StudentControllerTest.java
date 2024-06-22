package com.hasanalmunawr.SpringBoot_Mockito.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


/*https://medium.com/javarevisited/restful-api-testing-in-java-with-mockito-controller-layer-f4605f8ffaf3*/
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper mapper;

    private Student student;
    private StudentRequestDto requestDto;

    @BeforeEach
    void setUp() {
        student = new Student(
                1,
                "hasan",
                "almunawar",
                "mele"
        );
        requestDto = new StudentRequestDto(
                "hasan",
                "almunawar",
                "mele"
        );
    }

    @Test
    void addStudent() throws Exception {
        mockMvc.perform(
                post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto))
        ).andExpect(
                status().isCreated()
        );

    }

    @Test
    void getAllStudents() throws Exception {
        // Mock data
        StudentDto student1 = new StudentDto(
                1,  "hasan",
                "almunawar",
                "mele");
        StudentDto student2 = new StudentDto(2,
                "hasan",
                "warwar",
                "mele");

        given(studentService.getAllStudents())
                .willReturn(Arrays.asList(student1, student2));

        // Performing an HTTP GET request to fetch all students
        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("hasan"))
                .andExpect(jsonPath("$[0].lastName").value("almunawar"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("hasan"))
                .andExpect(jsonPath("$[1].lastName").value("warwar"));

    }

    @Test
    void getStudentById() throws Exception {
        int studentId = 1;
        StudentDto student = new StudentDto(
                studentId,
                "hasan",
                "almunawar",
                "mele");

        given(studentService.findById(studentId))
                .willReturn(student);

        // Performing an HTTP GET request to fetch a student by ID
        mockMvc.perform(get("/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.firstName").value("hasan"))
                .andExpect(jsonPath("$.lastName").value("almunawar"));
    }


    @Test
    void deleteStudentById() throws Exception {
        int studentId = 1;

//        given(studentService.deleteById(studentId));
//

        // Performing an HTTP DELETE request to delete a student by ID
        mockMvc.perform(delete("/student/{id}", studentId))
                .andExpect(status().isOk());
    }
}