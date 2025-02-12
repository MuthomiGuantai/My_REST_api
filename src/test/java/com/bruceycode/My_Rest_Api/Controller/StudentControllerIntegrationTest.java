package com.bruceycode.My_Rest_Api.Controller;

import com.bruceycode.My_Rest_Api.controller.StudentController;
import com.bruceycode.My_Rest_Api.model.Student;
import com.bruceycode.My_Rest_Api.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    public StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetStudents() throws Exception {

        Student student1 = new Student(
                1L,
                "Bruce Guantai",
                "bruce.guantai@gmail.com",
                LocalDate.of(1995, Month.DECEMBER,5));
        Student student2 = new Student(
                2L,
                "Victor Guantai",
                "victor.guantai@gmail.com",
                LocalDate.of(2002, Month.MARCH, 1));
        Mockito.when(studentService.getStudents()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Bruce Guantai"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Victor Guantai"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student(1L,
                "Bruce Guantai",
                "bruce.guantai@gmail.com",
                LocalDate.of(1995, Month.DECEMBER, 5));
        Mockito.when(studentService.getStudent(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bruce Guantai"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("bruce.guantai@gmail.com"));
    }

    @Test
    public void testRegisterNewStudent() throws Exception {

        Student student = new Student(null,
                "Timothy Guantai",
                "timothy.guantai@gmail.com",
                LocalDate.of(2007, Month.JANUARY, 1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studentService, Mockito.times(1)).addNewStudent(Mockito.any(Student.class));
    }

    @Test
    public void testDeleteStudent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studentService, Mockito.times(1)).deleteStudent(1L);
    }

    @Test
    public void testUpdateStudent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/student/1")
                        .param("name", "Bruce Muthomi")
                        .param("email", "bruce.muthomi@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(studentService, Mockito.times(1))
                .updateStudent(1L, "Bruce Muthomi", "bruce.muthomi@gmail.com");
    }
}


